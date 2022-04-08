package com.training.jpa.service;

import com.training.jpa.entity.LogRegistry;
import com.training.jpa.repository.LogRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogRegistryService {
    private final LogRegistryRepository logRegistryRepository;
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLogRegistry(final String text) {
        final LogRegistry logRegistry = new LogRegistry();
        logRegistry.setMethod(text);
        logRegistryRepository.save(logRegistry);
    }
}
