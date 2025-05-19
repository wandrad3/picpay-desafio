package com.br.wes.pay.application.contracts;

import com.br.wes.pay.domain.Transaction;
import com.br.wes.pay.domain.Wallet;

import java.util.List;
import java.util.Optional;

public interface ConsultarTrasactionGateway {
    
    Transaction findById(Integer id);
    List<Transaction> listTransactions(String payer, String payee);
}
