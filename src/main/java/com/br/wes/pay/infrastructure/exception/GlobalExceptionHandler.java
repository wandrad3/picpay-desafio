package com.br.wes.pay.infrastructure.exception;


import com.br.wes.pay.application.service.exception.InvalidTransactionException;
import com.br.wes.pay.application.service.exception.UnauthorizedTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ResponseError> handleInvalidTransactionException(InvalidTransactionException ex) {
        ResponseError error = new ResponseError(
                400,
                ex.getMessage(),
                "The transaction is invalid. Please check the details and try again."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<ResponseError> handleUnauthorizedTransactionException(UnauthorizedTransactionException ex) {
        ResponseError error = new ResponseError(
                403,
                ex.getMessage(),
                "The transaction is unauthorized. Ensure proper permissions are in place."
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}