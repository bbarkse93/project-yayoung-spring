package com.example.team_project._core.errors.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomRestfullException extends RuntimeException {

	private HttpStatus status;
	
	public CustomRestfullException(String message, HttpStatus httpStatus) {
		super(message);
		this.status = httpStatus;
	}
	 // 부모생성자 호출
	}

