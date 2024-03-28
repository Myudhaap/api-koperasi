package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.type_cash.TypeCashReq;
import com.enigma.koperasi.model.dto.response.type_cash.TypeCashRes;
import com.enigma.koperasi.model.entity.TypeCash;
import com.enigma.koperasi.model.mapper.TypeCashMapper;
import com.enigma.koperasi.repository.TypeCashRespository;
import com.enigma.koperasi.service.TypeCashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeCashServiceImpl implements TypeCashService {
  private final TypeCashRespository  typeCashRespository;
  private final TypeCashMapper typeCashMapper;
  @Override
  public TypeCashRes getOrSave(TypeCashReq req) {
    Optional<TypeCash> typeCashExist = typeCashRespository.findTypeCashByName(req.getTypeCashName());

    if(typeCashExist.isEmpty()){
      TypeCash typeCash = typeCashMapper.convertToEntity(req);
      typeCashRespository.insertAndFlush(typeCash);
      return typeCashMapper.convertToDto(typeCash);
    }

    return typeCashMapper.convertToDto(typeCashExist.get());
  }
}
