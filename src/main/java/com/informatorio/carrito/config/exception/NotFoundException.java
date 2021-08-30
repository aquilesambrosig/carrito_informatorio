package com.informatorio.carrito.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class NotFoundException extends RuntimeException {


    public NotFoundException() {

    }
    public NotFoundException(String message) {
        super(message);
    }
    
}
