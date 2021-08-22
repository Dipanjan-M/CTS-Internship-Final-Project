package com.cts.models.requestModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {

	@NotNull(message = "First Name cannot be null")
	@NotEmpty(message = "Firstname can't be empty")
	@NotBlank(message = "Firstname can't be blank")
	@Size(min = 3, max = 30, message = "First Should be between 5 to 30 characters")
	@Pattern(regexp = "^[A-Z]{1}[a-z]+", message = "Firstname must match the recomended pattern")
	String firstName;

	@NotNull(message = "Lastname cannot be null")
	@NotEmpty(message = "Lastname can't be empty")
	@NotBlank(message = "Lastname can't be blank")
	@Size(min = 3, max = 30, message = "Last Name Should be between 5 to 30 letters")
	@Pattern(regexp = "^[A-Z]{1}[a-z]+", message = "Lastname must match the recomended pattern")
	String lastName;

	@NotNull(message = "Email cannot be null")
	@NotBlank(message = "Email id can't be blank")
	@NotEmpty(message = "Email id can't be empty")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must match the recomended pattern")
	String emailId;

	@NotNull(message = "Password cannot be null")
	@NotBlank(message = "Password can't be blank")
	@NotEmpty(message = "Password can't be empty")
	String password;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
