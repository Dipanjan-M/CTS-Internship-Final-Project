package com.cts.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.User;
import com.cts.models.requestModels.PasswordChangeRequest;
import com.cts.models.requestModels.SignUpRequest;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.AccountService;

@RestController
public class UserController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * Creates a new account will non-admin privileges 
	 * Account needs to be activated by admin  
	 * @param signUpRequest
	 * @return
	 * @throws ApiException
	 */
	@PostMapping("/user/create")
	public ResponseEntity<?> registerAccount(@Valid @RequestBody SignUpRequest signUpRequest) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200,
				"Your account has been created successfully. Wait sometime while admin activates your accesses",
				accountService.createNewAccount(signUpRequest)));
	}
	
	/**
	 * Updates password for an existing user
	 * @param userId
	 * @param passwordChangeReq
	 * @return 
	 * @throws ApiException
	 */
	@PutMapping("/change-password/{userId}")
	public ResponseEntity<?> changePassword(@PathVariable int userId,
			@RequestBody PasswordChangeRequest passwordChangeReq) throws ApiException {
		User user = accountService.changeAccountPassword(userId, passwordChangeReq);
		return ResponseEntity.ok(new ApiResponse(200, "Password updated successfully", user));
	}

}
