package com.training.jpa.repository;

import com.training.jpa.controller.ContractRequest;
import com.training.jpa.entity.Contract;

import java.util.List;

public interface ContractCustomRepository {
    List<Contract> search(ContractRequest contractRequest);
}
