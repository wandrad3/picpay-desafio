package com.br.wes.pay.infrastructure.api.feign.impl;

import com.br.wes.pay.application.contracts.AuthorizerGateway;
import com.br.wes.pay.domain.Authorization;
import com.br.wes.pay.domain.AuthorizationData;
import com.br.wes.pay.domain.Transaction;
import com.br.wes.pay.infrastructure.api.feign.AuthorizationApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationImpl implements AuthorizerGateway {


    private final AuthorizationApi authorizationApi;

    public AuthorizationImpl(AuthorizationApi authorizationApi) {
        this.authorizationApi = authorizationApi;
    }


    @Override
    @CircuitBreaker(name = "authorization-circuit",
            fallbackMethod = "fallbackAuthorize")
    @Retry(name = "authorization-retry")
    public Authorization authorize(Transaction transaction) {

        return authorizationApi.authorize().getBody().toDomain();


    }

    /**
     * Método de fallback para o serviço de autorização.
     * Este método será chamado quando o serviço de autorização falhar.
     *
     * @param authorization a transação que falhou na autorização
     * @param t             a exceção que causou a falha
     * @return um objeto Authorization mockado
     */
    public Authorization fallbackAuthorize(Transaction authorization, Throwable t) {
        return new Authorization(
                "fail",
                new AuthorizationData(false)
        );
    }

}
