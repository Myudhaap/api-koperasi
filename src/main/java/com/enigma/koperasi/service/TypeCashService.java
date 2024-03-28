package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.type_cash.TypeCashReq;
import com.enigma.koperasi.model.dto.response.type_cash.TypeCashRes;

public interface TypeCashService {
  TypeCashRes getOrSave(TypeCashReq req);
}
