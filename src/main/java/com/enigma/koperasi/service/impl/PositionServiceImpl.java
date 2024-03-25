package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.model.entity.Position;
import com.enigma.koperasi.model.mapper.PositionMapper;
import com.enigma.koperasi.repository.PositionRepository;
import com.enigma.koperasi.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
  private final PositionRepository positionRepository;
  private final PositionMapper positionMapper;
  @Override
  public PositionRes getOrSave(PositionReq req) {
    Optional<Position> positionExist = positionRepository.findPositionByName(req.getPosition());

    if(positionExist.isEmpty()){
      Position position = Position.builder()
          .id(UUID.randomUUID().toString())
          .positionName(req.getPosition())
          .build();
      positionRepository.insertAndFlush(position);

      return positionMapper.convertToDto(position);
    }

    return positionMapper.convertToDto(positionExist.get());
  }
}
