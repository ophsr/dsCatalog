package com.devsuperior.dsCatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.devsuperior.dsCatalog.services.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErro> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}