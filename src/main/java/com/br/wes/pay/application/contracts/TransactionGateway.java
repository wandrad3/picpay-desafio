package com.br.wes.pay.application.contracts;

import com.br.wes.pay.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionGateway {


    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Integer id);

    List<Transaction> listTransactions(String payer, String payee);
}
