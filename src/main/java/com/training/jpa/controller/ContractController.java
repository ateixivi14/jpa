package com.training.jpa.controller;

import com.training.jpa.dto.ContractDto;
import com.training.jpa.entity.Contract;
import com.training.jpa.repository.ClientResumeResponse;
import com.training.jpa.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {

   private final ContractService contractService;

    @GetMapping("/list")
    public List<ContractDto> getClients() {
        return contractService.getContracts();
    }

    @PostMapping("/find-by-contract-id")
    public ContractDto getContractById(@RequestBody ContractRequest contractRequest) {
        return contractService.getContractById(contractRequest.getId());
    }

    @PostMapping("/find-by-id")
    public String getContractById2(@RequestBody ContractRequest contractRequest) {
        return contractService.getContractById2(contractRequest.getId());
    }
    
    @GetMapping("/resume")
    public List<ClientResumeResponse> getResume() {
        return contractService.getResume();
    }

    @PutMapping("/{contractId}/remove")
    public void removeClient(@PathVariable Long contractId) {
        contractService.removeContractById(contractId);
    }


    @GetMapping("/search")
    public List<Contract> search(ContractRequest contractRequest) {
        return contractService.search(contractRequest);
    }
    
    @PostMapping("/update-credit")
    public int updateCredit(@RequestBody UpdateCreditRequest updateCreditRequest) throws InterruptedException {
        return contractService.updateContractCredit(updateCreditRequest.getId(), updateCreditRequest.getAmount());
    }

}
