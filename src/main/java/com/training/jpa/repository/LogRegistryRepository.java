package com.training.jpa.repository;

import com.training.jpa.entity.LogRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRegistryRepository extends JpaRepository<LogRegistry, Long> {
}
