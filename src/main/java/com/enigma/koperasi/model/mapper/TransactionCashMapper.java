package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.transaction_cash.TransactionCashRes;
import com.enigma.koperasi.model.entity.TransactionSaving;
import org.springframework.stereotype.Component;

@Component
public class TransactionCashMapper {
  public TransactionCashRes convertToDto(TransactionSaving req, CashReq cashReq){
    return TransactionCashRes.builder()
        .id(req.getId())
        .employee(req.getEmployee().getName())
        .member(req.getMember().getName())
        .typeCash(req.getTypeCash().getTypeCashName())
        .trxDate(req.getTrxDate())
        .amount(req.getAmount())
        .description(req.getDescription())
        .build();
  }

  public TransactionCashRes convertToDto(TransactionSaving req){
    return TransactionCashRes.builder()
        .id(req.getId())
        .employee(req.getEmployee().getName())
        .member(req.getMember().getName())
        .typeCash(req.getTypeCash().getTypeCashName())
        .trxDate(req.getTrxDate())
        .amount(req.getAmount())
        .description(req.getDescription())
        .build();
  }
}
