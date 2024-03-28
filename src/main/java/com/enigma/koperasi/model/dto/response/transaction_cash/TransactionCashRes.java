package com.enigma.koperasi.model.dto.response.transaction_cash;

import com.enigma.koperasi.constant.ETypeSaving;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
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
public class TransactionCashRes {
  private String id;
  private String member;
  private ETypeSaving typeCash;
  private String employee;
  private Date trxDate;
  private Long amount;
  private Long totalAmount;
  private String description;
}
