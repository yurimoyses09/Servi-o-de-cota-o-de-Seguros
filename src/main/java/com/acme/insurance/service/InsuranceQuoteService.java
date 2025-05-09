package com.acme.insurance.service;

import com.acme.insurance.dtos.*;
import com.acme.insurance.utils.InsuranceQuoteValidator;
import com.acme.insurance.messaging.SenderMessage;
import com.acme.insurance.models.InsuranceQuote;
import com.acme.insurance.repository.InsuranceQuoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class InsuranceQuoteService {

    private static final Logger logger = Logger.getLogger(InsuranceQuoteService.class.getName());
    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private final OfferService offerService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final InsuranceQuoteValidator insuranceQuoteValidator;

    private final InsuranceQuoteRepository repository;

    public InsuranceQuoteService(InsuranceQuoteRepository repository, ModelMapper mapper, SenderMessage message, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, OfferService offerService, ProductService productService, InsuranceQuoteValidator insuranceQuoteValidator) {
        this.repository = repository;
        this.mapper = mapper;
        this.rabbitTemplate = rabbitTemplate;
        this.offerService = offerService;
        this.productService = productService;
        this.insuranceQuoteValidator = insuranceQuoteValidator;
    }

    public InsuranceQuote getQuotation(Integer id_insurance_quote){
        return repository.findById(id_insurance_quote).orElse(null);
    }

    public InsuranceQuote createQuotation(InsuranceQuoteRequest insuranceQuoteRequest) throws IOException, InterruptedException {

        logger.info("Buscando oferta...");
        Optional<OfferResponse> offer = offerService.getOffer(insuranceQuoteRequest.getOffer_id());

        logger.info("Buscando produto...");
        Optional<ProductResponse> product = productService.getProduct(insuranceQuoteRequest.getProduct_id());

        if (offer.isEmpty() || product.isEmpty()) {
            logger.info("Offer or Product not found");
            throw new IllegalArgumentException("Offer or Product not found");
        }

        logger.info("Validando regras");
        insuranceQuoteValidator.Validate(insuranceQuoteRequest, offer.get(), product.get());

        InsuranceQuote insuranceQuote = mapper.map(insuranceQuoteRequest, InsuranceQuote.class);

        logger.info("Salvando cotação em base de dados");
        InsuranceQuote entity = repository.saveAndFlush(insuranceQuote);

        logger.info("Enviando cotação para geração de policy");
        rabbitTemplate.convertAndSend("policy-issued", mapper.map(entity, PolicyRequest.class));

        return entity;
    }
}
