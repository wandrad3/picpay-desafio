package com.br.wes.pay.application.contracts;


import com.br.wes.pay.domain.Transaction;
import com.br.wes.pay.domain.Wallet;

public interface Validator {

    public void validate(Transaction transaction);
    public void validTransaction(Transaction transaction, Wallet payer, Wallet payee);
}
