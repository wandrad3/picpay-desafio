package com.br.wes.pay.infrastructure.api.feign;


import com.br.wes.pay.domain.Transaction;
import com.br.wes.pay.infrastructure.api.dto.AuthorizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authorizationApi", url = "${apps.servicos.api-autorizacao.url}")
public interface AuthorizationApi {

    @GetMapping("/api/v2/authorize")
    ResponseEntity<AuthorizationDTO> authorize();
}