package com.elior.imagesmanager.exceptions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsAdvice {

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handlError(Exception e) {
		return new ErrorDetails(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
				"BAD_REQUEST", e.getMessage(), 400);
	}
}
