package com.br.wes.pay.application.contracts;

import com.br.wes.pay.infrastructure.api.dto.TransactionDTO;

public interface NotificationGateway {

    void sendNotification(TransactionDTO transaction);
}
