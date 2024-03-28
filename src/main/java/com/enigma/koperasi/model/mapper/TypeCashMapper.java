package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.type_cash.TypeCashReq;
import com.enigma.koperasi.model.dto.response.type_cash.TypeCashRes;
import com.enigma.koperasi.model.entity.TypeCash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TypeCashMapper {
  public TypeCash convertToEntity(TypeCashReq req){
    return TypeCash.builder()
        .id(req.getId())
        .typeCashName(req.getTypeCashName())
        .build();
  }

  public TypeCash convertToEntity(TypeCashRes req){
    return TypeCash.builder()
        .id(req.getId())
        .typeCashName(req.getTypeCashName())
        .build();
  }

  public TypeCashRes convertToDto(TypeCash req){
    return TypeCashRes.builder()
        .id(req.getId())
        .typeCashName(req.getTypeCashName())
        .build();
  }
}
