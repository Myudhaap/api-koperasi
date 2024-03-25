package com.enigma.koperasi.model.dto.request.member;

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
public class UpdateMemberReq {
  private String id;
  private String name;
  private String address;
  private String email;
  private String phone;
  private Date birthOfDate;
  private EStatus status;
}
