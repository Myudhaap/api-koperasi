package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.entity.Cash;
import com.enigma.koperasi.model.mapper.CashMapper;
import com.enigma.koperasi.repository.CashRepository;
import com.enigma.koperasi.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {
  private final CashRepository cashRepository;
  private final CashMapper cashMapper;
  @Override
  public CashRes save(CashReq req) {
    Cash cash = cashMapper.convertToEntity(req);
    cashRepository.storeAndFlush(cash);

    return cashMapper.convertToDto(cash);
  }

  @Override
  public CashRes findById(String id) {
    Cash cash = cashRepository.findCashById(id)
        .orElseThrow(() -> new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Cash not found",
            HttpStatus.NOT_FOUND
        ));

    return cashMapper.convertToDto(cash);
  }
}
