package com.br.wes.pay.application.service;

import com.br.wes.pay.application.contracts.ConsultarTrasactionGateway;
import com.br.wes.pay.application.contracts.TransactionGateway;
import com.br.wes.pay.application.service.exception.NotFoundTransactionException;
import com.br.wes.pay.domain.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ConsultarTransactionService implements ConsultarTrasactionGateway {

    private final TransactionGateway transactionGateway;

    public ConsultarTransactionService(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public Transaction findById(Integer id) {

        return transactionGateway.findById(id).orElseThrow(() -> new NotFoundTransactionException("Transaction not found"));

    }

    @Override
    public Transaction listByPayee() {
        return null;
    }
}
