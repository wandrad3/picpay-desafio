package com.br.wes.pay.application.contracts;

import com.br.wes.pay.domain.Authorization;
import com.br.wes.pay.domain.Transaction;

public interface AuthorizerGateway {

    public Authorization authorize(Transaction transaction);
}
