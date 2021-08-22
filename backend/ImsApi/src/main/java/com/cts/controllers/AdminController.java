package com.cts.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.requestModels.SignUpRequest;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.AccountService;

@RestController
public class AdminController {

	@Autowired
	private AccountService accountService;

	/**
	 * Creates new activated user with admin privileges
	 * 
	 * @param signUpReq
	 * @return
	 * @throws ApiException
	 */
	@PostMapping("/admin/create")
	public ResponseEntity<?> createNewAdmin(@Valid @RequestBody SignUpRequest signUpReq) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "New admin account has been created successfully.",
				accountService.createNewAdminAccount(signUpReq)));
	}

	/**
	 * Fetches all users presented in the database
	 * 
	 * @return
	 * @throws ApiException
	 */
	@GetMapping("/admin/get-all-users")
	public ResponseEntity<?> getAllUsers() throws ApiException {
		return ResponseEntity
				.ok(new ApiResponse(200, "Fetched all users from database", accountService.fetchAllUsers()));
	}

	/**
	 * Fetches all new registered inactive users
	 * 
	 * @return
	 * @throws ApiException
	 */
	@GetMapping("/admin/new-users")
	public ResponseEntity<?> getNewUsers() throws ApiException {
		return ResponseEntity
				.ok(new ApiResponse(200, "Fetched all new users from database", accountService.fetchNewUsers()));
	}

	/**
	 * Fetches all deactivated users
	 * 
	 * @return
	 * @throws ApiException
	 */
	@GetMapping("/admin/deactivated-users")
	public ResponseEntity<?> getDeactivatedUsers() throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Fetched all deactivated users from database",
				accountService.fetchDeactivatedUsers()));
	}
	
	
	/**
	 * Toggles activate status of users
	 * @param id
	 * @return
	 */
	@PutMapping("/admin/activate-deactivate-user/{id}")
	public ResponseEntity<?> activateUser(@PathVariable int id) {
		return ResponseEntity.ok(new ApiResponse(200, "User has been activated successfully", accountService.activateUser(id)));
	}

}
