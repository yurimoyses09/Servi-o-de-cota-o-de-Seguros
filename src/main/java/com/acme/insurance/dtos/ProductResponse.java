package com.acme.insurance.dtos;

import java.time.ZonedDateTime;
import java.util.List;

import java.util.UUID;

public class ProductResponse {
    private UUID id;
    private String name;
    private ZonedDateTime created_at;
    private boolean active;
    private List<UUID> offers;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<UUID> getOffers() {
        return offers;
    }

    public void setOffers(List<UUID> offers) {
        this.offers = offers;
    }
}
