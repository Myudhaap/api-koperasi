package com.enigma.koperasi.model.dto.response.cash;

import com.enigma.koperasi.constant.EIsActive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashRes {
  private String id;
  private String description;
  private Long totalCash;
  private EIsActive status;
}
