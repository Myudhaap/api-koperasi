package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.entity.Cash;
import org.springframework.stereotype.Component;

@Component
public class CashMapper {
  public Cash convertToEntity(CashReq req){
    return Cash.builder()
        .id(req.getId())
        .description(req.getDescription())
        .totalCash(req.getTotalCash())
        .status(req.getStatus())
        .build();
  }

  public Cash convertToEntity(CashRes req){
    return Cash.builder()
        .id(req.getId())
        .description(req.getDescription())
        .totalCash(req.getTotalCash())
        .status(req.getStatus())
        .build();
  }

  public CashRes convertToDto(Cash req){
    return CashRes.builder()
        .id(req.getId())
        .description(req.getDescription())
        .totalCash(req.getTotalCash())
        .status(req.getStatus())
        .build();
  }

  public CashReq convertToReq(CashRes req){
    return CashReq.builder()
        .id(req.getId())
        .description(req.getDescription())
        .totalCash(req.getTotalCash())
        .status(req.getStatus())
        .build();
  }
}
