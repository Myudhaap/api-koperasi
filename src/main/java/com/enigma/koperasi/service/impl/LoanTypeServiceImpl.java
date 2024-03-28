package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.loan.LoanTypeReq;
import com.enigma.koperasi.model.dto.response.loan.LoanTypeRes;
import com.enigma.koperasi.model.entity.LoanType;
import com.enigma.koperasi.model.mapper.LoanTypeMapper;
import com.enigma.koperasi.repository.LoanTypeRepository;
import com.enigma.koperasi.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
  private final LoanTypeRepository loanTypeRepository;
  private final LoanTypeMapper loanTypeMapper;

  @Override
  public LoanTypeRes create(LoanTypeReq req) {
    LoanType loanType = loanTypeMapper.convertToEntity(req);
    loanTypeRepository.save(loanType);

    return loanTypeMapper.convertToDto(loanType);
  }

  @Override
  public LoanTypeRes update(LoanTypeReq req) {
    LoanTypeRes existLoanType = findById(req.getId());

    LoanType loanType = loanTypeMapper.convertToEntity(req);
    loanTypeRepository.save(loanType);

    return loanTypeMapper.convertToDto(loanType);
  }

  @Override
  public void delete(String id) {
    LoanTypeRes existLoanType = findById(id);

    LoanType loanType = loanTypeMapper.convertToEntity(existLoanType);
    loanType.setActive(false);
    loanTypeRepository.save(loanType);
  }

  @Override
  public List<LoanTypeRes> findAll() {
    List<LoanType> loanTypes = loanTypeRepository.findAll();

    return loanTypes.stream()
        .map(loanTypeMapper::convertToDto)
        .toList();
  }

  @Override
  public LoanTypeRes findById(String id) {
    LoanType loanType = loanTypeRepository.findById(id)
        .orElseThrow(() -> new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Loan Type not found",
            HttpStatus.NOT_FOUND
        ));

    return loanTypeMapper.convertToDto(loanType);
  }
}
