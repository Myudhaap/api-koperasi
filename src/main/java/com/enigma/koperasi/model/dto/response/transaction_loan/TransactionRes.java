package com.enigma.koperasi.model.dto.response.transaction_loan;

import com.enigma.koperasi.constant.EApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRes {
  private String id;
  private String loanTypeId;
  private String instalmentTypeId;
  private String memberId;
  private String employeeId;
  private Double nominal;
  private Long approvedAt;
  private EApprovalStatus approvalStatus;
  private List<TransactionDetailRes> loanTransactionDetails;
  private Long createdAt;
  private Long updatedAt;
}
