package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import org.springframework.data.domain.Page;

public interface CashService {
  CashRes save(CashReq req);
  CashRes findById(String id);
  Page<CashRes> findAll(int page, int size);
  CashRes update(CashReq req);
}
