package com.br.wes.pay.infrastructure.api.dto;

import com.br.wes.pay.domain.Authorization;
import com.br.wes.pay.domain.AuthorizationData;

public record AuthorizationDTO(
        String status,
        AuthorizationData data
        ) {
    public Authorization toDomain() {
        return new Authorization.Builder()
                .status(this.status)
                .data(this.data.toDomain())
                .build();
    }
}
