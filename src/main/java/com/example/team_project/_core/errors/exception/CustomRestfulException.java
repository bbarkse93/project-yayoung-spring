package com.example.team_project._core.errors.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomRestfulException extends RuntimeException {

    private HttpStatus status;

    public CustomRestfulException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
    // 부모생성자 호출
}
