package com.enigma.koperasi.model.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAuthRes {
  private String id;
  private String username;
  private String role;
  private String token;
}
