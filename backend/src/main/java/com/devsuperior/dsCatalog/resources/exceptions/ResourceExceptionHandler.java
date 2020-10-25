package com.devsuperior.dsCatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.devsuperior.dsCatalog.services.exceptions.DataBaseException;
import com.devsuperior.dsCatalog.services.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErro> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardErro> database(DataBaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addErro(f.getField(),f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }
}