package com.training.jpa.repository;

public interface ClientResumeResponse {
    int getAmount();
    String getName();
    Long getId();

    void setId(Long contractId);
    void setName(String name);
    void setAmount(int amount);
}
