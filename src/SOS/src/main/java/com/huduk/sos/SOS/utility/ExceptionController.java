package com.huduk.sos.SOS.utility;

import java.io.IOException;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.huduk.sos.SOS.dto.ErrorInfo;
import com.huduk.sos.SOS.exception.SOSException;

@RestControllerAdvice
public class ExceptionController {

    private final Environment environment;

    public ExceptionController(Environment environment) {
        this.environment = environment;
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorInfo> handleIOException(IOException exception) {
        ErrorInfo error = new ErrorInfo(environment.getProperty("SERVICE.IO_EXCEPTION"));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SOSException.class)
    public ResponseEntity<ErrorInfo> handleSOSException(SOSException exception) {
        ErrorInfo error = new ErrorInfo(environment.getProperty(exception.getMessage()));
        return new ResponseEntity<>(error, exception.getStatus());
    }
}
