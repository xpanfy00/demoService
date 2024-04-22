package com.example.demoservice.repository;

import com.example.demoservice.model.TransactionLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionLimitRepository extends JpaRepository<TransactionLimit, Long> {
    boolean existsByAccount(Long account);
    TransactionLimit findAllByAccount(Long accountFrom);


}