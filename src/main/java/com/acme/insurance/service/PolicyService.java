package com.acme.insurance.service;

import com.acme.insurance.dtos.PolicyRequest;
import com.acme.insurance.models.InsuranceQuote;
import com.acme.insurance.repository.InsuranceQuoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PolicyService {

    private final InsuranceQuoteRepository repository;

    public PolicyService(InsuranceQuoteRepository repository) {
        this.repository = repository;
    }

    public InsuranceQuote createPolicy(PolicyRequest policyRequest){

        Optional<InsuranceQuote> insuranceQuote = repository.findById(policyRequest.getId_insurance_quote());

        if (insuranceQuote.isEmpty())
            return null;

        insuranceQuote.get().setInsurance_policy_id(GeneratedPolicy());
        insuranceQuote.get().setUpdated_at(LocalDateTime.now());

        return repository.save(insuranceQuote.get());
    }

    private Integer GeneratedPolicy(){
        return ThreadLocalRandom.current().nextInt(1, 100000);
    }
}
