package com.enigma.koperasi.model.dto.request.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTypeReq {
  private String id;
  private Double maxLoan;
  private Integer interest;
  private String type;
}
