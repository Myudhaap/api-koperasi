package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.entity.Cash;
import com.enigma.koperasi.model.mapper.CashMapper;
import com.enigma.koperasi.repository.CashRepository;
import com.enigma.koperasi.service.CashService;
import lombok.RequiredArgsConstructor;
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
    cash.setId(UUID.randomUUID().toString());
    cashRepository.insertAndFlush(cash);

    return cashMapper.convertToDto(cash);
  }
}
