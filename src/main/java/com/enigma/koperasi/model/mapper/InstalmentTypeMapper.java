package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.instalment.InstalmentTypeReq;
import com.enigma.koperasi.model.dto.response.instalment.InstalmentTypeRes;
import com.enigma.koperasi.model.entity.InstalmentType;
import org.springframework.stereotype.Component;

@Component
public class InstalmentTypeMapper {
  public InstalmentType convertToEntity(InstalmentTypeReq req){
    return InstalmentType.builder()
        .id(req.getId())
        .instalmentType(req.getInstalmentType())
        .build();
  }

  public InstalmentType convertToEntity(InstalmentTypeRes req){
    return InstalmentType.builder()
        .id(req.getId())
        .instalmentType(req.getInstalmentType())
        .build();
  }

  public InstalmentTypeRes convertToDto(InstalmentType req){
    return InstalmentTypeRes.builder()
        .id(req.getId())
        .instalmentType(req.getInstalmentType())
        .build();
  }
}
