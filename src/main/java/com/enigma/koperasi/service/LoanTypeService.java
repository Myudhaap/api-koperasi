package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.loan.LoanTypeReq;
import com.enigma.koperasi.model.dto.response.loan.LoanTypeRes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoanTypeService {
  LoanTypeRes create(LoanTypeReq req);
  LoanTypeRes update(LoanTypeReq req);
  void delete(String id);
  Page<LoanTypeRes> findAll(int page, int size);
  LoanTypeRes findById(String id);
}
