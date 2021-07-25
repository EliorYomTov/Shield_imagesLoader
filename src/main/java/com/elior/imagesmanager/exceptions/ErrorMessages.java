package com.elior.imagesmanager.exceptions;

public enum ErrorMessages {
	NOT_FOUND_BY_ID("No Image with id %d was found");

	private String message;

	private ErrorMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
