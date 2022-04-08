package com.training.jpa.dto;

import com.training.jpa.entity.Client;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ClientDto {
    private final Long id;
    private final String name;
    private final boolean deleted;
    private final Long contractId;

    public static ClientDto toDto (Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .contractId(client.getContract() != null ? client.getContract().getId() : null)
                .name(client.getName())
                .build();

    }
}
