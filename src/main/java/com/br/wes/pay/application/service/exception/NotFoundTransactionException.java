package com.br.wes.pay.application.service.exception;

public class NotFoundTransactionException extends RuntimeException{
    public NotFoundTransactionException(String message) {
        super(message);
    }
}
