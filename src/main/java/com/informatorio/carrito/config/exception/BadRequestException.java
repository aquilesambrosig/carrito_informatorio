package com.informatorio.carrito.config.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException  {

    public BadRequestException() {

    }

    public BadRequestException(String message) {
        super(message);
        
    }
    
}
