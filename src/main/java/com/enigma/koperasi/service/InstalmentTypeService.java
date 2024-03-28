package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.instalment.InstalmentTypeReq;
import com.enigma.koperasi.model.dto.response.instalment.InstalmentTypeRes;

import java.util.List;

public interface InstalmentTypeService {
  InstalmentTypeRes create(InstalmentTypeReq req);
  InstalmentTypeRes update(InstalmentTypeReq req);
  void delete(String id);
  List<InstalmentTypeRes> findAll();
  InstalmentTypeRes findById(String id);
}
