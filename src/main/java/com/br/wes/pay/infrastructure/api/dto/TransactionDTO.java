package com.br.wes.pay.infrastructure.api.dto;

import com.br.wes.pay.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        Long id,
        Long payer,
        Long payee,
        BigDecimal amount,
        LocalDateTime createdAt) {

    public Transaction toDomain() {

        return new Transaction(
                id,
                payer,
                payee,
                amount,
                LocalDateTime.now()
        );
    }
}
