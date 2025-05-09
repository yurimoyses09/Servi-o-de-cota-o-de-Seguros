package com.acme.insurance.service;

import com.acme.insurance.dtos.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private final ObjectMapper objectMapper;

    public ProductService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<ProductResponse> getProduct(UUID guid) {
        try {
            URI uri = URI.create("http://mock-catalog:8080/api/v1/product/" + guid);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                logger.warning("Product not found for GUID: " + guid);
                return Optional.empty();
            }

            if (response.statusCode() != 200) {
                logger.severe("Failed to retrieve product: HTTP " + response.statusCode() + " - " + response.body());
                return Optional.empty();
            }

            ProductResponse productResponse = objectMapper.readValue(response.body(), ProductResponse.class);
            return Optional.of(productResponse);

        } catch (IOException | InterruptedException e) {
            logger.severe("Error fetching product with GUID " + guid + ": " + e.getMessage());
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }
}
