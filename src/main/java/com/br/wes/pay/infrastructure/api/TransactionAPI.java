package com.br.wes.pay.infrastructure.api;


import com.br.wes.pay.infrastructure.api.dto.TransactionRequestDto;
import com.br.wes.pay.infrastructure.exception.ResponseError;
import com.br.wes.pay.infrastructure.exception.ValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.checkerframework.checker.regex.qual.Regex;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/transaction")
@Tag(name = "Transaction", description = "Transaction API")
public interface TransactionAPI {

    @PostMapping
    @Operation(
            summary = "API para criar uma nova transação",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Transação criada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TransactionRequestDto.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "The transaction is invalid. Please check the details and try again.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseError.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "403",
                            description = "The transaction is unauthorized. Ensure proper permissions are in place.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseError.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "412",
                            description = "Make sure the fields are valid before sending request.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ValidationError.class)
                            )
                    )
            }
    )
    ResponseEntity<?> createTransaction(
            @RequestBody
            @Valid
            TransactionRequestDto transactionDto
    );

    @GetMapping("/{id}")
    @Operation(
            summary = "API para buscar uma nova transação por id",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Transação encontrada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TransactionRequestDto.class)
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "The transaction was not found. Please, check the ID and try again.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseError.class)
                            )
                    )
            }
    )
    ResponseEntity<?> buscarPorId(
            @PathVariable(name = "id")
            @NotNull
            Integer id
    );

    @GetMapping
    @Operation(
            summary = "API para listar todas as transações",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Lista de transações encontrada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TransactionRequestDto.class)
                            )
                    )
            }
    )
    ResponseEntity<List<TransactionRequestDto>> listarTransacoes(
            @RequestParam(name = "payer")
            @NotNull
            @Pattern(regexp = "^\\d+$", message = "O valor deve conter apenas números")
            String payer,
            @RequestParam(name = "payee")
            @NotNull
            @Pattern(regexp = "^\\d+$", message = "O valor deve conter apenas números")
            String payee

    );

}
