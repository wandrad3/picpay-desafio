package com.br.wes.pay.application.service;


import com.br.wes.pay.application.contracts.NotificationGateway;
import com.br.wes.pay.domain.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationGateway notificationProducer;

    public NotificationService(NotificationGateway notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction){

        notificationProducer.sendNotification(transaction.toDto());
    }
}
