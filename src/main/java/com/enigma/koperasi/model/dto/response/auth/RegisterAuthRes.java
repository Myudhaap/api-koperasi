package com.enigma.koperasi.model.dto.response.auth;

import com.enigma.koperasi.model.dto.response.role.RoleRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterAuthRes {
  private String username;
  private RoleRes role;
}
