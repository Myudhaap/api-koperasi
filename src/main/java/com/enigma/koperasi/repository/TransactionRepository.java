package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.LoanTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<LoanTransaction, String> {
  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        INSERT INTO t_trx_loan(id, member_id, loan_type_id, instalment_type_id, nominal, created_at)
        VALUES(
          :#{#loanTransaction.id},
          :#{#loanTransaction.member.id},
          :#{#loanTransaction.loanType.id},
          :#{#loanTransaction.instalmentType.id},
          :#{#loanTransaction.nominal},
          :#{#loanTransaction.createdAt}
        )
      """
  )
  void insert(LoanTransaction loanTransaction);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        UPDATE t_trx_loan
        SET
          employee_id = :#{#loanTransaction.employee.id},
          approved_at = :#{#loanTransaction.approvedAt},
          approval_status = :#{#loanTransaction.approvalStatus.name()},
          updated_at = :#{#loanTransaction.updatedAt}
        WHERE id = :#{#loanTransaction.id}
      """
  )
  void update(LoanTransaction loanTransaction);

  @Transactional
  default void store(LoanTransaction loanTransaction){
    if(loanTransaction.getId() == null){
      loanTransaction.setId(UUID.randomUUID().toString());
      insert(loanTransaction);
    }else{
      update(loanTransaction);
    }
  }

  @Transactional
  default void storeAndFlush(LoanTransaction loanTransaction){
    if(loanTransaction.getId() == null){
      loanTransaction.setId(UUID.randomUUID().toString());
      insert(loanTransaction);
    }else{
      update(loanTransaction);
    }

    flush();
  }

  @Query(
      nativeQuery = true,
      value = """
        SELECT * FROM t_trx_loan WHERE id = :id
      """
  )
  Optional<LoanTransaction> findLoanTransactionById(String id);

  @Query(
      nativeQuery = true,
      value = """
          SELECT * FROM t_trx_loan
        """,
      countQuery = """
          SELECT COUNT(*) FROM t_trx_loan
        """
  )
  Page<LoanTransaction> findLoanTransactionAll(Pageable pageable);


}
