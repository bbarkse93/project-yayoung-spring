package com.example.team_project._core.errors;

import com.example.team_project._core.errors.exception.*;
import com.example.team_project._core.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e){
        return new ResponseEntity<>(e.body(), e.status());
    }


    // TODO 언약 : 이거 핸들러 어떻게 할지 물어보고 정하기
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e){
        ApiUtils.ApiResult<?> apiResult = ApiUtils.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomRestfullException.class)
    public String basicException(CustomRestfullException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+ e.getMessage() +"');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthorizedException(UnAuthorizedException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+ e.getMessage() +"');");
        sb.append("location.href='/admin/login';");
        sb.append("</script>");
        return sb.toString();
    }
}
