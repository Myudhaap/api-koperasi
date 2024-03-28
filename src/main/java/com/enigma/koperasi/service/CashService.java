package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;

public interface CashService {
  CashRes save(CashReq req);
  CashRes findById(String id);
}
