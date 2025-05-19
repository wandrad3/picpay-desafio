package com.br.wes.pay.application.service;

import com.br.wes.pay.application.contracts.ConsultarTrasactionGateway;
import com.br.wes.pay.application.contracts.TransactionGateway;
import com.br.wes.pay.application.service.exception.NotFoundTransactionException;
import com.br.wes.pay.domain.Transaction;
import com.br.wes.pay.domain.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Transaction> listTransactions(String payer,String payee) {

        return transactionGateway.listTransactions(payer, payee);
    }
}
