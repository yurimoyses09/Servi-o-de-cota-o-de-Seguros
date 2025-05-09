package com.acme.insurance.messaging;

import com.acme.insurance.dtos.PolicyIssuedMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

@Component
public class PolicyConsumer {

    private static final Logger logger = Logger.getLogger(PolicyConsumer.class.getName());

    @Autowired
    private final ObjectMapper mapper;

    public PolicyConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @RabbitListener(queues = RabbitConfig.POLICY_ISSUED_QUEUE)
    public void handlePolicyIssued(PolicyIssuedMessage message) throws IOException, InterruptedException {

        logger.info("Mensagem recebida: " + message);

        logger.info("Enviando para geração de policy");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/policy"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(message)))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Policy gerada com sucesso! Código HTTP: " + response.body());
    }
}