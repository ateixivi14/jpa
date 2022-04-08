package com.training.jpa.service;

import com.training.jpa.dto.ClientDto;
import com.training.jpa.entity.Client;
import com.training.jpa.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getClients() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientRepository.findAll().forEach(client -> clientDtoList.add(ClientDto.toDto(client)));
        return clientDtoList;
    }

    public Collection<ClientDto> getActiveClients() {
        Collection<ClientDto> clientDtoActiveList =  new ArrayList<>();
        clientRepository.findAllActiveClientsNative().forEach(client -> clientDtoActiveList.add(ClientDto.toDto(client)));
        return clientDtoActiveList;
    }

    public List<ClientDto> getClientsByPagination() {
        List<ClientDto> clientDtoList = new ArrayList<>();

        Page<Client> page = clientRepository.findAllClientsWithPagination(PageRequest.of(1, 2));

        if (page.hasContent()) {
            page.getContent().forEach(client -> clientDtoList.add(ClientDto.toDto(client)));
        }
        return clientDtoList;
    }

    public void removeClientById(Long id) {
       clientRepository.deleteClientById(id);
    }

}
