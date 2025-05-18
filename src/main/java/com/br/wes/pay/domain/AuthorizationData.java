package com.br.wes.pay.domain;

public record AuthorizationData(
        boolean authorization
) {
    public AuthorizationData toDomain() {
        return new AuthorizationData(
                this.authorization
        );
    }
}
