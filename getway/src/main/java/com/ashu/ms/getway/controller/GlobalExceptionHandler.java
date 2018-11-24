package com.ashu.ms.getway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ashu.ms.common.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorResponse> processRequestBindingException(Exception ex) {
		LOGGER.error("api failed ", ex);
		ErrorResponse msg = new ErrorResponse(ex);
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

}
