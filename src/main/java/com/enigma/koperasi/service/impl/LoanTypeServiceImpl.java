package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.loan.LoanTypeReq;
import com.enigma.koperasi.model.dto.response.loan.LoanTypeRes;
import com.enigma.koperasi.model.entity.LoanType;
import com.enigma.koperasi.model.mapper.LoanTypeMapper;
import com.enigma.koperasi.repository.LoanTypeRepository;
import com.enigma.koperasi.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    loanTypeRepository.store(loanType);

    return loanTypeMapper.convertToDto(loanType);
  }

  @Override
  public LoanTypeRes update(LoanTypeReq req) {
    LoanTypeRes existLoanType = findById(req.getId());

    LoanType loanType = loanTypeMapper.convertToEntity(existLoanType);
    loanType.setInterest(req.getInterest());
    loanType.setType(req.getType());
    loanType.setMaxLoan(req.getMaxLoan());
    loanTypeRepository.store(loanType);

    return loanTypeMapper.convertToDto(loanType);
  }

  @Override
  public void delete(String id) {
    LoanTypeRes existLoanType = findById(id);

    LoanType loanType = loanTypeMapper.convertToEntity(existLoanType);
    loanType.setActive(false);
    loanTypeRepository.store(loanType);
  }

  @Override
  public Page<LoanTypeRes> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);

    Page<LoanType> loanTypes = loanTypeRepository.findLoanTypeAll(pageable);

    List<LoanTypeRes> loanTypeRes = loanTypes.getContent().stream()
        .map(loanTypeMapper::convertToDto)
        .toList();

    return  new PageImpl<>(loanTypeRes, pageable, loanTypes.getTotalElements());
  }

  @Override
  public LoanTypeRes findById(String id) {
    LoanType loanType = loanTypeRepository.findLoanTypeById(id)
        .orElseThrow(() -> new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Loan Type not found",
            HttpStatus.NOT_FOUND
        ));

    return loanTypeMapper.convertToDto(loanType);
  }
}
