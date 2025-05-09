package com.acme.insurance.dtos;

import com.acme.insurance.config.RulesException;

public class CustomerRequest {
    private String document_number;
    private String name;
    private String type;
    private String gender;
    private String date_of_birth;
    private String email;
    private Long phone_number;

    public void validate(){
        if (this.document_number.isEmpty())
            throw new RulesException("document_number deve estar prenchido");
        if (this.name.isEmpty())
            throw new RulesException("name deve estar prenchido");
        if (this.type.isEmpty())
            throw new RulesException("type deve estar prenchido");
        if (this.gender.isEmpty())
            throw new RulesException("gender deve estar prenchido");
        if (this.date_of_birth.isEmpty())
            throw new RulesException("date_of_birth deve estar prenchido");
        if (this.email.isEmpty())
            throw new RulesException("email deve estar prenchido");
        if (this.phone_number == null)
            throw new RulesException("phone_number deve estar prenchido");
    }

    // Getters e Setters
    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
}
