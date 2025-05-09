package com.acme.insurance.controller;

import com.acme.insurance.dtos.InsuranceQuoteRequest;
import com.acme.insurance.dtos.InsuranceQuoteResponse;
import com.acme.insurance.models.InsuranceQuote;
import com.acme.insurance.service.InsuranceQuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/quotations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsuranceController {

    private static final Logger logger = Logger.getLogger(InsuranceController.class.getName());

    @Autowired
    private final InsuranceQuoteService service;
    @Autowired
    private final ModelMapper mapper;

    public InsuranceController(InsuranceQuoteService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id_insurance_quote}")
    public ResponseEntity<?> getQuotation(@PathVariable("id_insurance_quote") Integer id_insurance_quote){
        logger.info("Buscando cotação de seguros: " + id_insurance_quote);

        InsuranceQuote response = service.getQuotation(id_insurance_quote);
        if (response == null){
            logger.info("Cotação nao foi criada: " + id_insurance_quote);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("Cotação encontrada: " + id_insurance_quote);
        return ResponseEntity.status(HttpStatus.FOUND).body((mapper.map(response, InsuranceQuoteResponse.class)));
    }

    @PostMapping
    public ResponseEntity<?> createQuotation(@RequestBody InsuranceQuoteRequest quoteDTO) throws IOException, InterruptedException {
        logger.info("Criando cotação de seguros");
        InsuranceQuote entity = service.createQuotation(quoteDTO);

        logger.info("Cotação criada com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(entity, InsuranceQuoteResponse.class));
    }
}
