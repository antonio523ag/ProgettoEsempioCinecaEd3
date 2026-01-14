package dev.antoniogrillo.primoprogettoesempio.exception;

import org.hibernate.PessimisticLockException;
import org.hibernate.StaleObjectStateException;
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

    @ExceptionHandler(StaleObjectStateException.class)
    public ResponseEntity<String> handleStaleObject(StaleObjectStateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).header("error",e.getMessage()).build();
    }

    @ExceptionHandler(PessimisticLockException.class)
    public ResponseEntity<String> handlePessimisticLock(PessimisticLockException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
