package com.br.wes.pay.infrastructure.api.impl;

import com.br.wes.pay.application.service.TransactionService;
import com.br.wes.pay.infrastructure.api.TransactionAPI;
import com.br.wes.pay.infrastructure.api.dto.TransactionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TransactionController implements TransactionAPI {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Override
    public ResponseEntity<TransactionDTO> createTransaction(TransactionDTO transactionDto) {

        var response = transactionService.create(transactionDto.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toDto());
    }
}
