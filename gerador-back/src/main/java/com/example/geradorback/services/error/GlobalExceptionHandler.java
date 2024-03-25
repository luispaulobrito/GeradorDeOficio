package com.example.geradorback.services.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ExceptionDto> handler (NegocioException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
}
