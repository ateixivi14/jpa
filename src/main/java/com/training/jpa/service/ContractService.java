package com.training.jpa.service;

import com.training.jpa.controller.ContractRequest;
import com.training.jpa.dto.ContractDto;
import com.training.jpa.entity.Contract;
import com.training.jpa.entity.LogRegistry;
import com.training.jpa.entity.Receipt;
import com.training.jpa.repository.ClientResumeResponse;
import com.training.jpa.repository.ContractRepository;
import com.training.jpa.repository.LogRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final LogRegistryService logRegistryService;
    private final ContractRepository contractRepository;

    public List<ContractDto> getContracts() {
        List<ContractDto> contractDtoList = new ArrayList<>();
        contractRepository.findAll().forEach(client -> contractDtoList.add(ContractDto.toDto(client)));
        return contractDtoList;
    }

    public ContractDto getContractById(Long contractId) {
        return ContractDto.toDto(contractRepository.findContractById(contractId));
    }

    public ContractDto getContractByClientId(Long clientId) {
        return ContractDto.toDto(contractRepository.findContractByClientId(clientId));
    }
    
    // For SUPPORTS, Spring first checks if an active transaction exists. If a transaction exists, 
    // then the existing transaction will be used. If there isn't a transaction, it is executed non-transactional
    // @Transactional(readOnly = true, noRollbackFor=RuntimeException.class, propagation = Propagation.SUPPORTS)
    @Transactional(readOnly = true)
    public String getContractById2(Long id)  {
       logRegistryService.saveLogRegistry("ContractService#findById, start"); // necessary to open a new transaction
       Contract contract = contractRepository.findById(id).orElseThrow(RuntimeException::new);
       List<Receipt> receiptList = contract.getReceipts();
       contract.setReference(contract.getReference() + "tururu");
       logRegistryService.saveLogRegistry("ContractService#findById, save");
       return contract.getReference();
    }
    
    public List<ClientResumeResponse> getResume() {
        return contractRepository.getResume();
    }

    public void removeContractById(Long id) {
        contractRepository.deleteContractById(id);
    }
    
    public List<Contract> search(ContractRequest contractRequest) {
        return contractRepository.search(contractRequest);
    } 
    
    public int updateContractCredit(Long id, int amount) throws InterruptedException {
        int currentAmount = 0;
        try {
            Contract contract = contractRepository.findContractById(id); // contract.version = 1
            currentAmount = contract.getCredit();
            contract.setCredit(currentAmount + amount);
            Thread.sleep(5000);
            contractRepository.save(contract);
            return currentAmount;
        } catch(StaleObjectStateException e) {
            System.out.println(e.getMessage());
        }

        return currentAmount;
    }

}
