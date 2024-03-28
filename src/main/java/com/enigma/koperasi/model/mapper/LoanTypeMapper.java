package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.loan.LoanTypeReq;
import com.enigma.koperasi.model.dto.response.loan.LoanTypeRes;
import com.enigma.koperasi.model.entity.LoanType;
import org.springframework.stereotype.Component;

@Component
public class LoanTypeMapper {
  public LoanType convertToEntity(LoanTypeReq req){
    return LoanType.builder()
        .id(req.getId())
        .maxLoan(req.getMaxLoan())
        .interest(req.getInterest())
        .type(req.getType())
        .build();
  }

  public LoanType convertToEntity(LoanTypeRes req){
    return LoanType.builder()
        .id(req.getId())
        .maxLoan(req.getMaxLoan())
        .interest(req.getInterest())
        .type(req.getType())
        .isActive(req.isActive())
        .build();
  }

  public LoanTypeRes convertToDto(LoanType req){
    return LoanTypeRes.builder()
        .id(req.getId())
        .maxLoan(req.getMaxLoan())
        .interest(req.getInterest())
        .type(req.getType())
        .isActive(req.isActive())
        .build();
  }
}
