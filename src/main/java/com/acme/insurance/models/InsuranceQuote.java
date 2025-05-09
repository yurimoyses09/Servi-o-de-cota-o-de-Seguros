package com.acme.insurance.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class InsuranceQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_insurance_quote;

    private Integer insurance_policy_id;
    private UUID product_id;
    private UUID offer_id;
    private String category;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at;
    private BigDecimal total_monthly_premium_amount;
    private BigDecimal total_coverage_amount;


    @ElementCollection
    @CollectionTable(name = "policy_coverages", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "coverage_name")
    @Column(name = "coverage_amount")
    private Map<String, BigDecimal> coverages = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "policy_assistances", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "assistance")
    private List<String> assistances = new ArrayList<>();

    @Embedded
    private Customer customer;

    // Getters e Setters
    public Integer getId_insurance_quote() {
        return id_insurance_quote;
    }

    public void setId_insurance_quote(Integer id_insurance_quote) {
        this.id_insurance_quote = id_insurance_quote;
    }

    public Integer getInsurance_policy_id() {
        return insurance_policy_id;
    }

    public void setInsurance_policy_id(Integer insurance_policy_id) {
        this.insurance_policy_id = insurance_policy_id;
    }

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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
