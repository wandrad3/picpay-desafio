package com.br.wes.pay.domain;


import com.br.wes.pay.infrastructure.api.dto.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        Long id,
        Long payer,
        Long payee,
        BigDecimal amount,
        LocalDateTime createdAt) {
    public Transaction {
        amount = amount.setScale(2); // configuracao de casa decimal com duas casas
    }

    public TransactionDTO toDto() {
        return new TransactionDTO(
                id,
                payer,
                payee,
                amount,
                createdAt
        );
    }

}
