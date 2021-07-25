package com.elior.imagesmanager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	private String timestamp;
	private String Key;
	private String Value;
	private int code;
	
//	private String message;
//	private HttpStatus error;
//	private int status;
//
//	public ErrorDetails(String message, HttpStatus error) {
//		this.message = message;
//		this.error = error;
//		this.status = error.value();
//	}
}
