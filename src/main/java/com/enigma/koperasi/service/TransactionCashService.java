package com.enigma.koperasi.service;

import com.enigma.koperasi.constant.ETypeSaving;
import com.enigma.koperasi.model.dto.request.transaction_cash.TransactionCashReq;
import com.enigma.koperasi.model.dto.response.transaction_cash.TransactionCashRes;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface TransactionCashService {
  TransactionCashRes transaction(TransactionCashReq req);
  Page<TransactionCashRes> findAll(
      String employeeName,
      String memberName,
      Integer amount,
      ETypeSaving typeSaving,
      Date startDate,
      Date endDate,
      int page,
      int size
  );

  Page<TransactionCashRes> findByMember(int page, int size);
}
