package com.training.jpa.controller;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {
    private Long id;
    private String name;
    private Long clientId;
}
