package com.br.wes.pay.infrastructure.exception;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class ResponseError {
    private int status;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ResponseError(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();

    }

    public int getStatus() {
        return status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }


}