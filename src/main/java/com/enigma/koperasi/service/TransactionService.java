package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionApproveReq;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionPayReq;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionReq;
import com.enigma.koperasi.model.dto.response.transaction_loan.TransactionRes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
  TransactionRes create(TransactionReq req);
  Page<TransactionRes> findAll(int page, int size);
  TransactionRes findById(String id);
  TransactionRes approve(String id);
  TransactionRes reject(String id);
  TransactionRes pay(TransactionPayReq req, String trxId);
}
