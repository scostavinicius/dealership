package com.dealership.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
    private String error;

    public ErrorResponse(String message, int status, String path, String error) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.error = error;
    }

    // Construtor sem "path", caso o erro retornado não tenha um
    public ErrorResponse(String message, int status, String error) {
        this(message, status, "N/A", error);
    }
}
