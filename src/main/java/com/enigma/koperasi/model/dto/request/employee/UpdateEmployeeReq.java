package com.enigma.koperasi.model.dto.request.employee;

import com.enigma.koperasi.constant.EPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeReq {
  private String id;
  private String name;
  private String address;
  private String email;
  private String phone;
  private EPosition position;
}
