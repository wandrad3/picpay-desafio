package com.br.wes.pay.domain;


public record Authorization(
        String status,
        AuthorizationData data
) {
    public boolean isAuthorized() {
        return data.authorization();
    }

    public static class Builder {
        private String status;
        private AuthorizationData data;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder data(AuthorizationData data) {
            this.data = data;
            return this;
        }

        public Authorization build() {
            return new Authorization(status, data);
        }
    }
}