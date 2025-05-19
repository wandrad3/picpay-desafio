package com.br.wes.pay.infrastructure.kafka.producer;

import com.br.wes.pay.application.contracts.NotificationGateway;
import com.br.wes.pay.infrastructure.api.dto.TransactionRequestDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer implements NotificationGateway {
    private final KafkaTemplate<String, TransactionRequestDto> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, TransactionRequestDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(TransactionRequestDto transaction){
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
