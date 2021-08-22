package com.cts.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.User;
import com.cts.models.requestModels.AuthenticationRequest;
import com.cts.models.responseModels.ApiResponse;
import com.cts.models.responseModels.AuthenticationResponse;
import com.cts.repository.UserRepository;
import com.cts.services.UserService;
import com.cts.util.JwtUtil;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Just a welcome message
	 * @return
	 */
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to Power Inventory Management System";
	}

	/**
	 * Authenticate an user and provide JWT authorization token
	 * @param authenticationRequest
	 * @return
	 * @throws ApiException
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws ApiException {
		String userName = authenticationRequest.getUserName();
		String password = authenticationRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (BadCredentialsException e) {
			throw new ApiException("Incorrect Username or Password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

		final String jwt = jwtUtil.generateToken(userDetails);

		Optional<User> loggedUser = userRepository.findByUserName(userName);
		AuthenticationResponse authResponse = null;
		if (loggedUser.isPresent()) {
			authResponse = new AuthenticationResponse(jwt, loggedUser.get());
		}
		
		User currentUser = loggedUser.get();
		currentUser.setLastLogin(new Date());
		userRepository.save(currentUser);

		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Successfully logged in", authResponse));

	}

}
