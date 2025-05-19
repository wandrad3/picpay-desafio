package com.br.wes.pay.application.contracts;

import com.br.wes.pay.infrastructure.api.dto.TransactionRequestDto;

public interface NotificationGateway {

    void sendNotification(TransactionRequestDto transaction);
}
