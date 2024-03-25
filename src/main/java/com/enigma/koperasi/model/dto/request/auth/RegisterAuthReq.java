package com.enigma.koperasi.model.dto.request.auth;

import com.enigma.koperasi.constant.EPosition;
import com.enigma.koperasi.constant.ERole;
import com.enigma.koperasi.constant.EStatus;
import com.enigma.koperasi.model.entity.Position;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RegisterAuthReq {
  private String username;
  private String password;
  private EPosition position;
  private String name;
  private String address;
  private EStatus status;
  private Date birthOfDate;
  private String email;
  private String phone;
}
