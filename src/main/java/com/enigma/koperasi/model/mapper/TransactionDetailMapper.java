package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.response.transaction_loan.TransactionDetailRes;
import com.enigma.koperasi.model.entity.LoanTransactionDetail;
import org.springframework.stereotype.Component;

@Component
public class TransactionDetailMapper {
  public LoanTransactionDetail convertToEntity(TransactionDetailRes req){
    return LoanTransactionDetail.builder()
        .id(req.getId())
        .loanStatus(req.getLoanStatus())
        .transactionDate(req.getTransactionDate())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .nominal(req.getNominal())
        .build();
  }

  public TransactionDetailRes convertToDto(LoanTransactionDetail req){
    return TransactionDetailRes.builder()
        .id(req.getId())
        .loanStatus(req.getLoanStatus())
        .transactionDate(req.getTransactionDate())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .nominal(req.getNominal())
        .build();
  }
}
