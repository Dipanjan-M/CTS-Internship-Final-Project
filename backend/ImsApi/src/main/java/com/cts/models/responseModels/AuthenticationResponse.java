package com.cts.models.responseModels;

import com.cts.models.entityModels.User;

public class AuthenticationResponse {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private String role;
	private final String token;

	public AuthenticationResponse(String token, User user) {
		super();
		this.token = token;
		this.id = user.getId();
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		if(user.isAdmin()) {
			this.role = "Admin";
		} else {
			this.role = "Employee";
		}
	}

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
