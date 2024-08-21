package com.example.capstone2.Advice;

import com.example.capstone2.Api.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;

public class ControllerAdvice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);

    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e) {
        String message = e.getMessage();
        return ResponseEntity.status(404).body(message);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);

    }
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity SQLException(SQLException e) {
        String message = e.getMessage();
        return ResponseEntity.status(500).body(message);
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

}