package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.loan.LoanTypeReq;
import com.enigma.koperasi.model.dto.response.loan.LoanTypeRes;

import java.util.List;

public interface LoanTypeService {
  LoanTypeRes create(LoanTypeReq req);
  LoanTypeRes update(LoanTypeReq req);
  void delete(String id);
  List<LoanTypeRes> findAll();
  LoanTypeRes findById(String id);
}
