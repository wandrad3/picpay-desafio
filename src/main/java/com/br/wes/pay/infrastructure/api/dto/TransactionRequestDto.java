package com.br.wes.pay.infrastructure.api.dto;

import com.br.wes.pay.domain.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDto(

        Long id,

        @NotNull
        @Pattern(regexp = "^\\d+$", message = "O valor deve conter apenas números")
        String  payer,

        @NotNull
        @Pattern(regexp = "^\\d+$", message = "O valor deve conter apenas números")
        String payee,

        @NotNull
        BigDecimal amount,

        LocalDateTime createdAt) {

    public Transaction toDomain() {

        return new Transaction(
                id,
                Long.parseLong(payer),
                Long.parseLong(payee),
                amount,
                LocalDateTime.now()
        );
    }
}
