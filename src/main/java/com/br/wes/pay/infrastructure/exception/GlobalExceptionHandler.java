package com.br.wes.pay.infrastructure.exception;


import com.br.wes.pay.application.service.exception.InvalidTransactionException;
import com.br.wes.pay.application.service.exception.NotFoundTransactionException;
import com.br.wes.pay.application.service.exception.UnauthorizedTransactionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

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

    @ExceptionHandler(NotFoundTransactionException.class)
    public ResponseEntity<ResponseError> handleNotFoundTransactionException(NotFoundTransactionException ex) {
        ResponseError error = new ResponseError(
                404,
                ex.getMessage(),
                "The transaction was not found. Please, check the ID and try again."
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;

        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Erro de validação de campos");
        err.setMessage("Valide os campos antes de enviar a requisição");
        err.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}