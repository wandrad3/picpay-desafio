package com.br.wes.pay.application.service;


import com.br.wes.pay.application.contracts.Validator;
import com.br.wes.pay.application.contracts.WalletGateway;
import com.br.wes.pay.application.service.exception.InvalidTransactionException;
import com.br.wes.pay.domain.Transaction;
import com.br.wes.pay.domain.Wallet;
import com.br.wes.pay.domain.enumemator.WalletType;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class TrasactionValidator implements Validator {

    private final WalletGateway walletRepository;

    public TrasactionValidator(WalletGateway walletRepository) {
        this.walletRepository = walletRepository;
    }

    /*
     * - the payer has a commom wallet type
     *  - the payer has enough balance
     *  - the payer is not the payee
     */
    public void validate(Transaction transaction) {
        Wallet payee = walletRepository.findById(transaction.payee()).get();

        Wallet payer = walletRepository.findById(transaction.payer()).get();

        validTransaction(transaction, payer, payee);
    }


    @Override
    public void validTransaction(Transaction transaction, Wallet payer, Wallet payee) {

    }

    private static void validaPagador(boolean payee, String transaction) {
        if (payee) {
            throw new InvalidTransactionException(transaction);
        }
    }

    private static void validaPagadorRecebedor(Transaction transaction, Wallet payer, Wallet payee) {
        validaPagador(Objects.equals(payer.id(), payee.id()), "Payer and payee cannot be the same - %s".formatted(transaction));
    }

    private static void validaTipoCliente(Wallet payer) {
        validaPagador(payer.type() != WalletType.COMUM.getValue(), "Payer must to have type 1");
    }

    private static void saldoSuficiente(Transaction transaction, Wallet payer) {
        validaPagador(payer.balance().compareTo(transaction.amount()) < 0, "Payer has insufficient balance - %s".formatted(transaction));
    }
}



