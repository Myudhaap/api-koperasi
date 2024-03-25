package com.enigma.koperasi.model.dto.response.member;

import com.enigma.koperasi.constant.EStatus;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.dto.response.user_credential.UserCredentialRes;
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
public class MemberRes {
  private String id;
  private UserCredentialRes userCredential;
  private CashRes cash;
  private String name;
  private String address;
  private String email;
  private String phone;
  private Date birthOfDate;
  private EStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean isActive;
}
