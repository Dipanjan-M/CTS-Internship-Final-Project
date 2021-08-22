package com.cts.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.models.responseModels.ApiResponse;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Executes to handle custom application specific exception i.e ApiException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> somethingWentWrong(ApiException ex) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
	}

	/**
	 * Overridden method to handle MethodArgumentNotValidException which may raise
	 * at the time of validation
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		return ResponseEntity.ok(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Input", errors));
	}

	/**
	 * Overridden method to handle HttpRequestMethodNotSupportedException which may
	 * raise at the time of request mapping. Suppose we are sending a PUT request to
	 * an URL which support GET method only then this type of exception will arise
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.ok(new ApiResponse(status.value(), ex.getMessage()));
	}

	/**
	 * Overridden method to handle HttpMessageNotReadableException
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.ok(new ApiResponse(status.value(), "Required request body is missing"));
	}

}
