package com.br.wes.pay.application.service.exception;

public class InvalidTransactionException extends  RuntimeException{

  public InvalidTransactionException(String message){
      super(message);
  }
}
