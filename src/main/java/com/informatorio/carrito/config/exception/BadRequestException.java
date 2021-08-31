package com.informatorio.carrito.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BadRequestException extends RuntimeException  {

    public BadRequestException() {

    }

    public BadRequestException(String message) {
        super(message);
        
    }
    
}
