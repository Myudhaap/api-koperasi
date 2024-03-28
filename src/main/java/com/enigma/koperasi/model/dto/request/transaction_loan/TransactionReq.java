package com.enigma.koperasi.model.dto.request.transaction_loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionReq {
  private String loanTypeId;
  private String instalmentTypeId;
  private String memberId;
  private Double nominal;
}
