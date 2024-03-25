package com.enigma.koperasi.model.dto.request.role;

import com.enigma.koperasi.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleReq {
  private String id;
  private ERole role;
}
