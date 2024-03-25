package com.enigma.koperasi.model.dto.response.user_credential;

import com.enigma.koperasi.model.dto.response.role.RoleRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialRes {
  private String id;
  private String username;
  private String password;
  private RoleRes role;
}
