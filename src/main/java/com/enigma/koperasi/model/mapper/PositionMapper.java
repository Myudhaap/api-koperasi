package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.model.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {
  public  Position convertToEntity(PositionReq req){
    return Position.builder()
        .id(req.getId())
        .positionName(req.getPosition())
        .build();
  }

  public  Position convertToEntity(PositionRes req){
    return Position.builder()
        .id(req.getId())
        .positionName(req.getPosition())
        .isActive(req.isActive())
        .build();
  }

  public  PositionRes convertToDto(Position req){
    return PositionRes.builder()
        .id(req.getId())
        .position(req.getPositionName())
        .isActive(req.isActive())
        .build();
  }
}
