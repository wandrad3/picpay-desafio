package com.br.wes.pay.infrastructure.api.impl;

import com.br.wes.pay.application.service.ConsultarTransactionService;
import com.br.wes.pay.application.service.CriarTransactionService;
import com.br.wes.pay.infrastructure.api.TransactionAPI;
import com.br.wes.pay.infrastructure.api.dto.TransactionRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TransactionController implements TransactionAPI {

    private final CriarTransactionService criarTransactionService;
    private final ConsultarTransactionService consultarTrasactionService;

    public TransactionController(CriarTransactionService transactionService,
                                 ConsultarTransactionService consultarTrasactionService) {
        this.criarTransactionService = transactionService;
        this.consultarTrasactionService = consultarTrasactionService;
    }


    @Override
    public ResponseEntity<TransactionRequestDto> createTransaction(TransactionRequestDto transactionDto) {

        var response = criarTransactionService.create(transactionDto.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toDto());
    }

    @Override
    public ResponseEntity<?> buscarPorId(Integer id) {

        var response = consultarTrasactionService.findById(id);

        return ResponseEntity.ok(response.toDto());

    }
}
