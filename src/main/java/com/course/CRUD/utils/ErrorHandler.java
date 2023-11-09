package com.course.CRUD.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        StringBuilder messageError = new StringBuilder();
        errors.forEach(f -> messageError.append(f.getField()).append(" ").append(f.getDefaultMessage()).append(" "));
        ErrorMessage errorMessage = new ErrorMessage(messageError.toString().toUpperCase(),HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
