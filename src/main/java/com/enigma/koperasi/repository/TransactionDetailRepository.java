package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        INSERT INTO t_trx_loan_detail(id, trx_loan_id, nominal, loan_status, created_at)
        VALUES(
          :#{#loanTransactionDetail.id},
          :#{#loanTransactionDetail.loanTransaction.id},
          :#{#loanTransactionDetail.nominal},
          :#{#loanTransactionDetail.loanStatus.name()},
          :#{#loanTransactionDetail.createdAt}
        )
      """
  )
  void insert(LoanTransactionDetail loanTransactionDetail);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        UPDATE t_trx_loan_detail
        SET
          loan_status = :#{#loanTransactionDetail.loanStatus.name()},
          updated_at = :#{#loanTransactionDetail.updatedAt},
          transaction_date = :#{#loanTransactionDetail.transactionDate}
        WHERE id = :#{#loanTransactionDetail.id}
      """
  )
  void update(LoanTransactionDetail loanTransactionDetail);

  @Transactional
  default void storeAndFlush(LoanTransactionDetail loanTransactionDetail){
    if(loanTransactionDetail.getId() == null){
      loanTransactionDetail.setId(UUID.randomUUID().toString());
      insert(loanTransactionDetail);
    }else{
      update(loanTransactionDetail);
    }

    flush();
  }

  @Query(
      nativeQuery = true,
      value = """
        SELECT * FROM t_trx_loan_detail WHERE id = :id
      """
  )
  Optional<LoanTransactionDetail> findLoanTransactionDetailById(String id);
}
