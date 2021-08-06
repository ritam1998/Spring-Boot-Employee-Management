package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),employeeNotFoundException.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DatabaseAccessException.class)
	public ResponseEntity<?> handleDatabaseFoundException(DatabaseAccessException databaseException,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),databaseException.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<?> handleProjectNotException(ProjectNotFoundException projectNotFoundException,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),projectNotFoundException.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
}
