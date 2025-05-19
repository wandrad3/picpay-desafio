package com.br.wes.pay.domain;


import com.br.wes.pay.infrastructure.api.dto.TransactionRequestDto;

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

    public TransactionRequestDto toDto() {
        return new TransactionRequestDto(
                id,
                payer,
                payee,
                amount,
                createdAt
        );
    }

}
