package com.training.jpa.repository;

import com.training.jpa.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();

    Client findClientById(Long id);

    Client findClientByContractId(Long id);

    @Query(value = "SELECT * FROM client c WHERE c.is_active = true",
            nativeQuery = true)
    Collection<Client> findAllActiveClientsNative();

    @Query(value = "SELECT * FROM client c ORDER BY id", nativeQuery = true)
    Page<Client> findAllClientsWithPagination(Pageable pageable);

    @Modifying
    @Transactional
    void deleteClientById(Long id);

}
