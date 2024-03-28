package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.ELoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = DbPath.TRANSACTION_DETAIL_SCHEMA)
public class LoanTransactionDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "transaction_date")
  private Long transactionDate;
  private Double nominal;
  @ManyToOne
  @JoinColumn(name = "trx_loan_id", referencedColumnName = "id")
  private LoanTransaction loanTransaction;
  @Column(name = "loan_status")
  @Enumerated(EnumType.STRING)
  private ELoanStatus loanStatus;
  @JoinColumn(name = "created_at")
  private Long createdAt;
  @JoinColumn(name = "updated_at")
  private Long updatedAt;
}
