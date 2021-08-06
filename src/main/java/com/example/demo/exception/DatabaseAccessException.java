package com.example.demo.exception;

public class DatabaseAccessException extends Exception{

	private static final long serialVersionUID = 1L;

	public DatabaseAccessException(String message) {
		super(message);
	}
}
