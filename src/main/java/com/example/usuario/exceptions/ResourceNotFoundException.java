package com.example.usuario.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3847309943462943979L;
	private String message;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
