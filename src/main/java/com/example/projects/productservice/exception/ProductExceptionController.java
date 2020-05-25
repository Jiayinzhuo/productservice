package com.example.projects.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
	@ExceptionHandler(value = ProductAlreadyExistException.class)
	public ResponseEntity<Object> exception(ProductAlreadyExistException exception) {
		return new ResponseEntity<>("Product already exist", HttpStatus.FOUND);
	}

	@ExceptionHandler(value = ProductNotfoundException.class)
	public ResponseEntity<Object> exception(ProductNotfoundException exception) {
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	}
}