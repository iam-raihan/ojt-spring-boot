package com.bjit.thymeleaf.error;

public class InvalidParseException extends RuntimeException {
	String message;

	public InvalidParseException() {
		super();
	}

	public InvalidParseException(String message) {
		super(message);
		this.message = message;
	}
}
