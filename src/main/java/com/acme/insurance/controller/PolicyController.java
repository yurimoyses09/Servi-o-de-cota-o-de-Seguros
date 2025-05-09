package com.acme.insurance.controller;

import com.acme.insurance.dtos.PolicyRequest;
import com.acme.insurance.dtos.PolicyResponse;
import com.acme.insurance.models.InsuranceQuote;
import com.acme.insurance.service.PolicyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/policy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PolicyController {

    private static final Logger logger = Logger.getLogger(PolicyController.class.getName());

    @Autowired
    private final PolicyService service;
    @Autowired
    private final ModelMapper mapper;

    public PolicyController(PolicyService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> createPolicy(@RequestBody PolicyRequest policyRequest){
        logger.info("Gerando policy para a cotação:" + policyRequest.getId_insurance_quote());

        InsuranceQuote entity = service.createPolicy(policyRequest);
        if (entity == null){
            logger.info("Cotação nao foi encontrada:" + policyRequest.getId_insurance_quote());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Cotação criada com sucesso:" + entity.getInsurance_policy_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(entity, PolicyResponse.class));
    }
}
