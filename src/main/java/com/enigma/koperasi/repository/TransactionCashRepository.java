package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.dto.response.transaction_cash.TransactionCashRes;
import com.enigma.koperasi.model.entity.TransactionSaving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface TransactionCashRepository extends JpaRepository<TransactionSaving, String>, JpaSpecificationExecutor<TransactionSaving> {
  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        INSERT INTO t_trx_cash(id, employee_id, member_id, type_cash_id, trx_date, amount, description)
        VALUES(
          :#{#transactionSaving.id},
          :#{#transactionSaving.employee.id},
          :#{#transactionSaving.member.id},
          :#{#transactionSaving.typeCash.id},
          :#{#transactionSaving.trxDate},
          :#{#transactionSaving.amount},
          :#{#transactionSaving.description}
        )
"""
  )
  void insert(TransactionSaving transactionSaving);

  @Transactional
  default void store(TransactionSaving transactionSaving){
    transactionSaving.setId(UUID.randomUUID().toString());
    insert(transactionSaving);
  }

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM t_trx_cash WHERE member_id = :memberId",
      countQuery = "SELECT COUNT(*) FROM t_trx_cash WHERE member_id = :memberId"
  )
  Page<TransactionSaving> findByMemberId(String memberId, Pageable pageable);
}
