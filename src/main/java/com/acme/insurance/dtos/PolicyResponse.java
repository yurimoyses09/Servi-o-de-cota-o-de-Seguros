package com.acme.insurance.dtos;

public class PolicyResponse {
    private Integer insurance_policy_id;
    private Integer id_insurance_quote;

    public Integer getInsurance_policy_id() {
        return insurance_policy_id;
    }

    public void setInsurance_policy_id(Integer insurance_policy_id) {
        this.insurance_policy_id = insurance_policy_id;
    }

    public Integer getId_insurance_quote() {
        return id_insurance_quote;
    }

    public void setId_insurance_quote(Integer id_insurance_quote) {
        this.id_insurance_quote = id_insurance_quote;
    }
}
