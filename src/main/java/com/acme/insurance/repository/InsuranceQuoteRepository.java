package com.acme.insurance.repository;

import com.acme.insurance.models.InsuranceQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceQuoteRepository extends JpaRepository<InsuranceQuote, Integer> {
}
