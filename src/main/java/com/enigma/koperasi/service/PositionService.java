package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import org.springframework.data.domain.Page;

public interface PositionService {
  PositionRes getOrSave(PositionReq req);
  Page<PositionRes> findAll(int page, int size);
  PositionRes findById(String id);
  PositionRes update(PositionReq req);
  void delete(String id);
}
