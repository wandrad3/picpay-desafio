package com.br.wes.pay.infrastructure.kafka.consumer;

import com.br.wes.pay.infrastructure.api.dto.TransactionRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    public NotificationConsumer(RestClient.Builder builder) {
    }

    @KafkaListener(topics = "transaction-notification", groupId = "payment-backend")
    public void receiveNotification(TransactionRequestDto transaction) {
        logger.info("Notificação recebida do producer: {}", transaction);
    }
}