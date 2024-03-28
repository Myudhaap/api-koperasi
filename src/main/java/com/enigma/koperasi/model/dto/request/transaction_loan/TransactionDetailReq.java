package com.enigma.koperasi.model.dto.request.transaction_loan;

import com.enigma.koperasi.constant.ELoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailReq {
  private String id;
  private String transactionId;
  private Long transactionDate;
  private Double nominal;
  private ELoanStatus loanStatus;
}
