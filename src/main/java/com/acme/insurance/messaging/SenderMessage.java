package com.acme.insurance.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderMessage {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public SenderMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String queue, String message) {
        rabbitTemplate.convertAndSend(queue, message);
    }
}
