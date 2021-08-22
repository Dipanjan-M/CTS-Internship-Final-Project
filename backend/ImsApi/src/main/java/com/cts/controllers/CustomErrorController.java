package com.cts.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.models.responseModels.ApiResponse;

@RestController
public class CustomErrorController implements ErrorController {

	public String getErrorPath() {
		return null;
	}

	/**
	 * In case of white label error page "/error" route response has been customized
	 * here
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/error")
	public ResponseEntity<?> handleError(HttpServletRequest request) {

		ApiResponse apiResponse = new ApiResponse();

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {

			Integer statusCode = Integer.valueOf(status.toString());
			apiResponse.setStatus(statusCode);

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				apiResponse.setMessage("Resource Not Found");
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				apiResponse.setMessage("Internal Server Error");
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				apiResponse.setMessage("Access Forbidden");
			} else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				apiResponse.setMessage("Requested method isn't allowed");
			} else {
				apiResponse.setMessage("Unidentified error");
			}
		}

		return ResponseEntity.ok(apiResponse);
	}

}
