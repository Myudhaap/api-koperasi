package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.entity.LoanTransactionDetail;
import com.enigma.koperasi.repository.TransactionDetailRepository;
import com.enigma.koperasi.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {
  private final TransactionDetailRepository transactionDetailRepository;

  @Override
  public LoanTransactionDetail save(LoanTransactionDetail req) {
    return transactionDetailRepository.saveAndFlush(req);
  }

  @Override
  public LoanTransactionDetail findById(String id) {
    return transactionDetailRepository.findById(id)
        .orElseThrow(() -> new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Loan transaction detail not found",
            HttpStatus.NOT_FOUND
        ));
  }
}
