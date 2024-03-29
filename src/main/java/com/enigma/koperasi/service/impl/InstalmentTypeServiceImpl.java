package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.instalment.InstalmentTypeReq;
import com.enigma.koperasi.model.dto.response.instalment.InstalmentTypeRes;
import com.enigma.koperasi.model.entity.InstalmentType;
import com.enigma.koperasi.model.mapper.InstalmentTypeMapper;
import com.enigma.koperasi.repository.InstalmentTypeRepository;
import com.enigma.koperasi.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
  private final InstalmentTypeRepository instalmentTypeRepository;
  private final InstalmentTypeMapper instalmentTypeMapper;

  @Override
  public InstalmentTypeRes create(InstalmentTypeReq req) throws DataIntegrityViolationException {
    InstalmentType instalmentType = instalmentTypeMapper.convertToEntity(req);
    try {
      instalmentTypeRepository.store(instalmentType);
    }catch (DataIntegrityViolationException e){
      throw new ApplicationException(
          HttpStatus.CONFLICT.name(),
          "Instalment was found, can't create",
          HttpStatus.CONFLICT
      );
    }

    return instalmentTypeMapper.convertToDto(instalmentType);
  }

  @Override
  public InstalmentTypeRes update(InstalmentTypeReq req) {
    InstalmentTypeRes existInstalmentType = findById(req.getId());

    InstalmentType instalmentType = instalmentTypeMapper.convertToEntity(existInstalmentType);
    instalmentType.setInstalmentType(req.getInstalmentType());
    instalmentTypeRepository.store(instalmentType);

    return instalmentTypeMapper.convertToDto(instalmentType);
  }

  @Override
  public void delete(String id) {
    InstalmentTypeRes existInstalmentType = findById(id);

    InstalmentType instalmentType = instalmentTypeMapper.convertToEntity(existInstalmentType);
    instalmentTypeRepository.delete(instalmentType);
  }

  @Override
  public Page<InstalmentTypeRes> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<InstalmentType> instalmentTypes = instalmentTypeRepository.findInstalmentTypeAll(pageable);

    List<InstalmentTypeRes> instalmentTypeRes = instalmentTypes.getContent().stream()
        .map(instalmentTypeMapper::convertToDto)
        .toList();

    return new PageImpl<>(instalmentTypeRes, pageable, instalmentTypes.getTotalElements());
  }

  @Override
  public InstalmentTypeRes findById(String id) {
    InstalmentType instalmentType = instalmentTypeRepository.findInstalmentTypeById(id)
        .orElseThrow(() -> new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Instalment Type not found",
            HttpStatus.NOT_FOUND
        ));

    return instalmentTypeMapper.convertToDto(instalmentType);
  }
}
