package com.training.jpa.repository;

import com.training.jpa.entity.Contract;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>, ContractCustomRepository {

    Contract findContractById(Long id);

   // @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "client_entity_graph")
    Contract findContractByClientId(Long id);

    @EntityGraph(attributePaths = {"client"})
    List<Contract> findAll();

    @Query("SELECT cli.name as name, con.id as id, sum(re.amount) as amount from Client cli join Contract con on cli.id = con.id " +
            "inner join Receipt re on con.id =  re.id GROUP BY cli.name, con.id")
    List<ClientResumeResponse> getResume();

    @Modifying
    @Transactional
    void deleteContractById(Long id);
}
