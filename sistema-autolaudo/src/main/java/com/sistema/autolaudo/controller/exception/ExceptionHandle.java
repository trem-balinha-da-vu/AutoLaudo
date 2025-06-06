package com.sistema.autolaudo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sistema.autolaudo.dto.common.BaseReturn;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handleConstraintViolation(BaseException exception, WebRequest request) {


        BaseReturn<Object> response = new BaseReturn<>();
        response.success = false;
        response.description = exception.getMessage();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}