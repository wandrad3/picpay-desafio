package com.br.wes.pay.infrastructure.api;


import com.br.wes.pay.infrastructure.api.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/transaction")
@Tag(name = "Transaction", description = "Transaction API")
public interface TransactionAPI {

    @PostMapping
    ResponseEntity<?> createTransaction(
            @RequestBody
            @Valid
            TransactionDTO transactionDto
    );

    @GetMapping("/{id}")
    ResponseEntity<?> buscarPorId(
            @PathVariable(name = "id")
            @NotNull
            Integer id
    );


}
