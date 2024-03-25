package com.enigma.koperasi.model.dto.request.employee;

import com.enigma.koperasi.constant.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeReq {
  private String id;
  private String userCredentialId;
  private String positionId;
  private String name;
  private String address;
  private String email;
  private String phone;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean isActive;
}
