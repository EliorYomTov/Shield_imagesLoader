package com.elior.imagesmanager.exceptions;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(ErrorMessages error, int id) {
		super(String.format(error.getMessage(), id));
	}
}
