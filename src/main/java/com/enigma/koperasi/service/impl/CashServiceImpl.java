package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.entity.Cash;
import com.enigma.koperasi.model.mapper.CashMapper;
import com.enigma.koperasi.repository.CashRepository;
import com.enigma.koperasi.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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

  @Override
  public Page<CashRes> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);

    Page<Cash> cashPage = cashRepository.findCashAll(pageable);

    List<CashRes> cashRes = cashPage.getContent().stream()
        .map(cashMapper::convertToDto).toList();

    return new PageImpl<>(cashRes, pageable, cashPage.getTotalElements());
  }

  @Override
  public CashRes update(CashReq req) {
    CashRes cashExist = findById(req.getId());

    Cash cash = cashMapper.convertToEntity(cashExist);
    if(req.getTotalCash() != null) cash.setTotalCash(req.getTotalCash());
    cash.setDescription(req.getDescription());
    cash.setStatus(req.getStatus());
    cashRepository.storeAndFlush(cash);

    return cashMapper.convertToDto(cash);
  }
}
