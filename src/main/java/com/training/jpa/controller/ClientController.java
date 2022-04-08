package com.training.jpa.controller;

import com.training.jpa.dto.ClientDto;
import com.training.jpa.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/list")
    public List<ClientDto> getClients() {
        return clientService.getClientsByPagination();
    }

    @GetMapping("/list-active")
    public Collection<ClientDto> getClientsActive() {
        return clientService.getActiveClients();
    }


   @PutMapping("/{clientId}/remove")
    public void removeClient(@PathVariable Long clientId) {
        clientService.removeClientById(clientId);
    }

}
