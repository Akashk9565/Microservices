package com.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.payload.ApiResponse;

@RestControllerAdvice
public class GlobaleExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse responce = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return  new ResponseEntity<ApiResponse>(responce,HttpStatus.NOT_FOUND);
		
	}
}