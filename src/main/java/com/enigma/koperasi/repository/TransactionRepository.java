package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<LoanTransaction, String> {
}
