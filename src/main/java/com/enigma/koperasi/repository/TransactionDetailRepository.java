package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
}
