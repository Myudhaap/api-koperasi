package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.model.entity.Position;
import com.enigma.koperasi.model.mapper.PositionMapper;
import com.enigma.koperasi.repository.PositionRepository;
import com.enigma.koperasi.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
          .positionName(req.getPosition())
          .isActive(true)
          .build();
      positionRepository.storeAndFlush(position);

      return positionMapper.convertToDto(position);
    }

    return positionMapper.convertToDto(positionExist.get());
  }

  @Override
  public Page<PositionRes> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);

    Page<Position> positions = positionRepository.findPositionAll(pageable);

    List<PositionRes> positionRes = positions.getContent().stream()
        .map(positionMapper::convertToDto).toList();

    return new PageImpl<>(positionRes, pageable, positions.getTotalElements());
  }

  @Override
  public PositionRes findById(String id) {
    Position positionExist = positionRepository.findPositionById(id).orElseThrow(() -> new ApplicationException(
        HttpStatus.NOT_FOUND.name(),
        "Position not found",
        HttpStatus.NOT_FOUND
    ));

    return positionMapper.convertToDto(positionExist);
  }

  @Override
  public PositionRes update(PositionReq req) {
    PositionRes positionExist = findById(req.getId());

    Position position = positionMapper.convertToEntity(positionExist);
    position.setPositionName(req.getPosition());
    positionRepository.storeAndFlush(position);

    return positionMapper.convertToDto(position);
  }

  @Override
  public void delete(String id) {
    PositionRes positionExist = findById(id);

    Position position = positionMapper.convertToEntity(positionExist);
    position.setActive(false);
    positionRepository.storeAndFlush(position);

    positionMapper.convertToDto(position);
  }
}
