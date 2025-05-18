package com.br.wes.pay.infrastructure.kafka.consumer;

import com.br.wes.pay.application.service.exception.NotificationException;
import com.br.wes.pay.domain.Notification;
import com.br.wes.pay.infrastructure.api.dto.TransactionDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationConsumer {

    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "payment-backend")
    public void receiveNotification(TransactionDTO transaction) {
        var response = restClient.get()
                .retrieve()
                .toEntity(Notification.class);

        if(response.getStatusCode().isError() || !response.getBody().message()){
            throw new NotificationException("Error sending notification");
        }
    }
}
