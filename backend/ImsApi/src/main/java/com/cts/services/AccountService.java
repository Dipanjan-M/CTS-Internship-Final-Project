package com.cts.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.User;
import com.cts.models.requestModels.PasswordChangeRequest;
import com.cts.models.requestModels.SignUpRequest;
import com.cts.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Creates a new deactivated account with non-admin privileges
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	public User createNewAccount(SignUpRequest request) throws ApiException {
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmailId(request.getEmailId());
		String textPassword = request.getPassword();
		String encryptedPassword = passwordEncoder.encode(textPassword);
		user.setPassword(encryptedPassword);
		String generatedUserName = request.getEmailId().strip().split("@")[0] + request.getFirstName().length()
				+ request.getLastName().length();
		user.setUserName(generatedUserName);
		user.setAdmin(false);
		user.setActiveUser(false);
		Date date = new Date();
		user.setDateOfAssociation(date);
		Optional<User> foundByEmail = userRepository.findByEmailId(user.getEmailId());
		if (foundByEmail.isPresent()) {
			throw new ApiException("Email ID - " + foundByEmail.get().getEmailId()
					+ " has already been registered with our system. Please use another one.");
		}
		return userRepository.save(user);
	}
	
	
	/**
	 * Create an activated user with admin privileges
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	public User createNewAdminAccount(SignUpRequest request) throws ApiException {
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmailId(request.getEmailId());
		String textPassword = request.getPassword();
		String encryptedPassword = passwordEncoder.encode(textPassword);
		user.setPassword(encryptedPassword);
		String generatedUserName = request.getEmailId().strip().split("@")[0] + request.getFirstName().length()
				+ request.getLastName().length();
		user.setUserName(generatedUserName);
		user.setAdmin(true);
		user.setActiveUser(true);
		Date date = new Date();
		user.setDateOfAssociation(date);
		Optional<User> foundByEmail = userRepository.findByEmailId(user.getEmailId());
		if (foundByEmail.isPresent()) {
			throw new ApiException("Email ID - " + foundByEmail.get().getEmailId()
					+ " has already been registered with our system. Please use another one.");
		}
		return userRepository.save(user);
	}
	
	/**
	 * Service to change password of an existing active user
	 * @param userId
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	public User changeAccountPassword(int userId, PasswordChangeRequest request) throws ApiException {
		User user = userRepository.getById(userId);
		if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			throw new ApiException("Please provide correct old password in order to update new password");
		}
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		return userRepository.save(user);
	}
	
	/**
	 * Fetch all users from database
	 * @return
	 * @throws ApiException
	 */
	public List<User> fetchAllUsers() throws ApiException {
		List<User> allUsers = userRepository.findAll();
		if(allUsers.isEmpty()) {
			throw new ApiException("No user found");
		}
		return allUsers;
	}
	
	/**
	 * Fetch all newly registered users
	 * @return
	 * @throws ApiException
	 */
	public List<User> fetchNewUsers() throws ApiException {
		List<User> newUsers = userRepository.findByActiveUserAndLastLogin(false, null);
		if(newUsers.isEmpty()) {
			throw new ApiException("No new user was found");
		}
		return newUsers;
	}
	
	/**
	 * Fetches all deactivated users
	 * @return
	 * @throws ApiException
	 */
	public List<User> fetchDeactivatedUsers() throws ApiException {
		List<User> newUsers = userRepository.findByActiveUserAndLastLoginNotNull(false);
		if(newUsers.isEmpty()) {
			throw new ApiException("No deactivated user was found");
		}
		return newUsers;
	}
	
	/**
	 * Toggles active state of the users
	 * @param userId
	 * @return
	 */
	public User activateUser(int userId) {
		User user = userRepository.getById(userId);
		user.setActiveUser(!user.isActiveUser());
		return userRepository.save(user);
	}
	
}
