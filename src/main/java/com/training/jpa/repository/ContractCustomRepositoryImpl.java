package com.training.jpa.repository;

import com.training.jpa.controller.ContractRequest;
import com.training.jpa.entity.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractCustomRepositoryImpl implements ContractCustomRepository {
    
    private final EntityManager entityManager;
    
    @Override
    public List<Contract> search(ContractRequest contractRequest) {
        ContractRequest contractRequest1 =  ContractRequest.builder()
                .name("jon")
                .id(1L)
                .clientId(1L)
                .build();
        
        Query query = entityManager.createQuery(createSqlQuery(contractRequest1));
        if (contractRequest1.getClientId() != null) {
            query.setParameter("clientId", contractRequest1.getClientId());
        }
        if (StringUtils.hasText(contractRequest1.getName())) {
            query.setParameter("clientName", contractRequest1.getName());
        }
        
        return query.getResultList();
    }
    
    private String createSqlQuery(ContractRequest contractRequest) {
        
        StringBuilder query = new StringBuilder();
        query.append("select distinct c FROM Contract c ");
        query.append("inner join Client i ON i.id = c.id ");
        query.append("WHERE");
        if (contractRequest.getClientId() != null)
            query.append(" i.id = :clientId AND ");
        if (StringUtils.hasText(contractRequest.getName())) {
            query.append(" i.name like :clientName ");
        } 
       // query.toString().substring(0, query.length()-5); 
        return query.toString();
    }
}
