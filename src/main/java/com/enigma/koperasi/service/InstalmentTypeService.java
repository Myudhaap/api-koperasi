package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.instalment.InstalmentTypeReq;
import com.enigma.koperasi.model.dto.response.instalment.InstalmentTypeRes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InstalmentTypeService {
  InstalmentTypeRes create(InstalmentTypeReq req);
  InstalmentTypeRes update(InstalmentTypeReq req);
  void delete(String id);
  Page<InstalmentTypeRes> findAll(int page, int size);
  InstalmentTypeRes findById(String id);
}
