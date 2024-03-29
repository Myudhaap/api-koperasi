package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.LoanType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        INSERT INTO m_loan_type(id, interest, maximum_loan, type, is_active)
        VALUES (
          :#{#loanType.id}, 
          :#{#loanType.interest}, 
          :#{#loanType.maxLoan}, 
          :#{#loanType.type}, 
          :#{#loanType.isActive}
        )
      """
  )
  void insert(LoanType loanType);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        UPDATE m_loan_type
        SET 
          interest = :#{#loanType.interest}, 
          maximum_loan = :#{#loanType.maxLoan}, 
          type = :#{#loanType.type}, 
          is_active = :#{#loanType.isActive}
        WHERE id = :#{#loanType.id}
      """
  )
  void update(LoanType loanType);

  @Transactional
  default void store(LoanType loanType){
    if(loanType.getId() == null){
      loanType.setId(UUID.randomUUID().toString());
      insert(loanType);
    }else{
      update(loanType);
    }
  }

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM m_loan_type WHERE id = :id AND is_active = true"
  )
  Optional<LoanType> findLoanTypeById(String id);

  @Query(
      nativeQuery = true,
      value = """
        SELECT * FROM m_loan_type WHERE is_active = true
      """,
      countQuery = """
        SELECT COUNT(*) FROM m_loan_type WHERE is_active = true
      """
  )
  Page<LoanType> findLoanTypeAll(Pageable pageable);
}
