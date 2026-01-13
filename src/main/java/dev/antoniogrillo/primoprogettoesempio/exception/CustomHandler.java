package dev.antoniogrillo.primoprogettoesempio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class CustomHandler {


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrity(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).header("error",e.getMessage()).build();
    }
}
