package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.response.transaction_loan.TransactionDetailRes;
import com.enigma.koperasi.model.dto.response.transaction_loan.TransactionRes;
import com.enigma.koperasi.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
  private final TransactionDetailMapper transactionDetailMapper;

  public LoanTransaction convertToEntity(
      TransactionRes req,
      Member member,
      LoanType loanType,
      InstalmentType instalmentType,
      Employee employee
  ){
    List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();
    if(req.getLoanTransactionDetails() != null){
      loanTransactionDetails = req.getLoanTransactionDetails()
          .stream()
          .map(transactionDetailMapper::convertToEntity).toList();
    }

    return LoanTransaction.builder()
        .id(req.getId())
        .loanType(loanType)
        .instalmentType(instalmentType)
        .employee(employee)
        .member(member)
        .nominal(req.getNominal())
        .approvedAt(req.getApprovedAt())
        .approvalStatus(req.getApprovalStatus())
        .loanTransactionDetails(loanTransactionDetails)
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .build();
  }

  public TransactionRes convertToDto(LoanTransaction req){
    List<TransactionDetailRes> loanTransactionDetails = new ArrayList<>();
    if(req.getLoanTransactionDetails() != null){
      loanTransactionDetails = req.getLoanTransactionDetails()
          .stream()
          .map(transactionDetailMapper::convertToDto).toList();
    }

    return TransactionRes.builder()
        .id(req.getId())
        .loanTypeId(req.getLoanType().getId())
        .instalmentTypeId(req.getInstalmentType().getId())
        .memberId(req.getMember().getId())
        .employeeId((req.getEmployee() != null ? req.getEmployee().getId() : null))
        .nominal(req.getNominal())
        .approvedAt(req.getApprovedAt())
        .approvalStatus(req.getApprovalStatus())
        .loanTransactionDetails(loanTransactionDetails)
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .build();
  }
}
