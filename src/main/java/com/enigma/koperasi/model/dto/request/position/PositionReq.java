package com.enigma.koperasi.model.dto.request.position;

import com.enigma.koperasi.constant.EPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionReq {
  private String id;
  private EPosition position;
}
