package com.huduk.sos.SOS.dto;

public class ErrorInfo {
    private String message;

    public ErrorInfo() {}

    public ErrorInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
