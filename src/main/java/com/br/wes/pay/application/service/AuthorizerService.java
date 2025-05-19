//package com.br.wes.pay.application.service;
//
//import com.br.wes.pay.application.contracts.AuthorizerGateway;
//import com.br.wes.pay.application.service.exception.UnauthorizedTransactionException;
//import com.br.wes.pay.domain.Authorization;
//import com.br.wes.pay.domain.Transaction;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import java.util.Objects;
//
//@Service
//public class AuthorizerService implements AuthorizerGateway {
//    private RestClient restClient;
//
//    public AuthorizerService(RestClient.Builder builder) {
//        this.restClient = builder
//                .baseUrl("https://util.devi.tools/api/v2/authorize")
//                .build();
//    }
//
//    public Authorization authorize(Transaction transaction) {
//
//        try {
//            var response = restClient.get()
//                    .retrieve()
//                    .toEntity(Authorization.class);
//            return response.getBody();
//
//        } catch (Exception e) {
//            throw new UnauthorizedTransactionException("Transaction not authorized");
//        }
//
//    }
//}
