package com.br.wes.pay.application.contracts;

import com.br.wes.pay.domain.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface TransactionGateway {


    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
}
