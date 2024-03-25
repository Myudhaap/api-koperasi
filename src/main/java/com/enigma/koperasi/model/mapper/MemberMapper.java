package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.entity.Cash;
import com.enigma.koperasi.model.entity.Member;
import com.enigma.koperasi.model.entity.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MemberMapper {
  private final UserCredentialMapper userCredentialMapper;
  private final CashMapper cashMapper;

  public Member convertToEntity(MemberReq req){
    return Member.builder()
        .id(req.getId())
        .name(req.getName())
        .email(req.getEmail())
        .phone(req.getPhone())
        .status(req.getStatus())
        .address(req.getAddress())
        .birthOfDate(req.getBirthOfDate())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .isActive(true)
        .build();
  }
  public Member convertToEntity(MemberRes req){
    return Member.builder()
        .id(req.getId())
        .name(req.getName())
        .email(req.getEmail())
        .phone(req.getPhone())
        .status(req.getStatus())
        .address(req.getAddress())
        .birthOfDate(req.getBirthOfDate())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .cash(cashMapper.convertToEntity(req.getCash()))
        .isActive(req.isActive())
        .build();
  }

  public MemberRes convertToDto(Member req){
    return MemberRes.builder()
        .id(req.getId())
        .name(req.getName())
        .email(req.getEmail())
        .phone(req.getPhone())
        .status(req.getStatus())
        .address(req.getAddress())
        .birthOfDate(req.getBirthOfDate())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .isActive(req.isActive())
        .cash(cashMapper.convertToDto(req.getCash()))
        .build();
  }
}
