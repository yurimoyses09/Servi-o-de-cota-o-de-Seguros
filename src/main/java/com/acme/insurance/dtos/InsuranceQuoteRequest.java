package com.acme.insurance.dtos;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InsuranceQuoteRequest {
    private UUID product_id;
    private UUID offer_id;
    private String category;
    private BigDecimal total_monthly_premium_amount;
    private BigDecimal total_coverage_amount;
    private Map<String, BigDecimal> coverages;
    private List<String> assistances;
    private CustomerRequest customer;

    // Getters e Setters
    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    public UUID getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(UUID offer_id) {
        this.offer_id = offer_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotal_monthly_premium_amount() {
        return total_monthly_premium_amount;
    }

    public void setTotal_monthly_premium_amount(BigDecimal total_monthly_premium_amount) {
        this.total_monthly_premium_amount = total_monthly_premium_amount;
    }

    public BigDecimal getTotal_coverage_amount() {
        return total_coverage_amount;
    }

    public void setTotal_coverage_amount(BigDecimal total_coverage_amount) {
        this.total_coverage_amount = total_coverage_amount;
    }

    public Map<String, BigDecimal> getCoverages() {
         return coverages;
    }

    public void setCoverages(Map<String, BigDecimal> coverages) {
        this.coverages = coverages;
    }

    public List<String> getAssistances() {
        return assistances;
    }

    public void setAssistances(List<String> assistances) {
        this.assistances = assistances;
    }

    public CustomerRequest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequest customer) {
        this.customer = customer;
    }
}
