package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.position.PositionRes;

public interface PositionService {
  PositionRes getOrSave(PositionReq req);
}
