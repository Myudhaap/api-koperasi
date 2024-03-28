package com.enigma.koperasi.service;


import com.enigma.koperasi.model.entity.LoanTransactionDetail;

public interface TransactionDetailService {
  LoanTransactionDetail save(LoanTransactionDetail req);
  LoanTransactionDetail findById(String id);
}
