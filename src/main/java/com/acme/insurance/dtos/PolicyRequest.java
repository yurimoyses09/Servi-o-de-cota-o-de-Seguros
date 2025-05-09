package com.acme.insurance.dtos;

import java.util.UUID;

public class PolicyRequest {
    private Integer id_insurance_quote;
    private UUID product_id;
    private UUID offer_id;

    public Integer getId_insurance_quote() {
        return id_insurance_quote;
    }

    public void setId_insurance_quote(Integer id_insurance_quote) {
        this.id_insurance_quote = id_insurance_quote;
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
}
