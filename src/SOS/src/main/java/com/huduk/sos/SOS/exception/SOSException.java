package com.huduk.sos.SOS.exception;

import org.springframework.http.HttpStatus;

public class SOSException extends RuntimeException{

    private static final long serialVersionUID = 7355608L;
    private final String message;
    private final HttpStatus status;

    public SOSException() {
        message = "GENERAL_MESSAGE";
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public SOSException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
}