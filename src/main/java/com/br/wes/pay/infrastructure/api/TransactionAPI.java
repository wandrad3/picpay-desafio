package com.br.wes.pay.infrastructure.api;


import com.br.wes.pay.infrastructure.api.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/transaction")
@Tag(name = "Transaction", description = "Transaction API")
public interface TransactionAPI {

    @PostMapping
    ResponseEntity<?> createTransaction(
            @RequestBody
            @Valid
            TransactionDTO transactionDto
    );

}
