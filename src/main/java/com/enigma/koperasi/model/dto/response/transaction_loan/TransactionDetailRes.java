package com.enigma.koperasi.model.dto.response.transaction_loan;

import com.enigma.koperasi.constant.ELoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailRes {
  private String id;
  private Long transactionDate;
  private Double nominal;
  private ELoanStatus loanStatus;
  private Long createdAt;
  private Long updatedAt;
}
