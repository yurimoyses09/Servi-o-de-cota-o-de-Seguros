package com.acme.insurance.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OfferResponse {

    public BigDecimal SumCoverageValue(Collection<BigDecimal> values){

        BigDecimal total = BigDecimal.ZERO;

        for (BigDecimal value : values){
            total = total.add(value);
        }
        return total;
    }

    private UUID id;
    private UUID product_id;
    private String name;
    private ZonedDateTime created_at;
    private boolean active;
    private Map<String, BigDecimal> coverages;
    private List<String> assistances;
    private MonthlyPremiumAmount monthly_premium_amount;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public MonthlyPremiumAmount getMonthly_premium_amount() {
        return monthly_premium_amount;
    }

    public void setMonthly_premium_amount(MonthlyPremiumAmount monthly_premium_amount) {
        this.monthly_premium_amount = monthly_premium_amount;
    }

    // Classe interna para monthly_premium_amount
    public static class MonthlyPremiumAmount {
        private BigDecimal max_amount;
        private BigDecimal min_amount;
        private BigDecimal suggested_amount;

        public BigDecimal getMax_amount() {
            return max_amount;
        }

        public void setMax_amount(BigDecimal max_amount) {
            this.max_amount = max_amount;
        }

        public BigDecimal getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(BigDecimal min_amount) {
            this.min_amount = min_amount;
        }

        public BigDecimal getSuggested_amount() {
            return suggested_amount;
        }

        public void setSuggested_amount(BigDecimal suggested_amount) {
            this.suggested_amount = suggested_amount;
        }
    }
}
