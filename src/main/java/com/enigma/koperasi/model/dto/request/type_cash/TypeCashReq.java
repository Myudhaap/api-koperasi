package com.enigma.koperasi.model.dto.request.type_cash;

import com.enigma.koperasi.constant.ETypeSaving;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeCashReq {
  private String id;
  private ETypeSaving typeCashName;
}
