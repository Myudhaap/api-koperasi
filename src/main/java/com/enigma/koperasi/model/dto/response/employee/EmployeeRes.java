package com.enigma.koperasi.model.dto.response.employee;

import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.model.dto.response.user_credential.UserCredentialRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRes {
  private String id;
  private UserCredentialRes userCredential;
  private PositionRes position;
  private String name;
  private String address;
  private String email;
  private String phone;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean isActive;
}
