package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.entity.Member;
import com.enigma.koperasi.model.mapper.MemberMapper;
import com.enigma.koperasi.repository.MemberRepository;
import com.enigma.koperasi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;
  @Override
  public MemberRes save(Member req) {
    req.setId(UUID.randomUUID().toString());

    memberRepository.save(req);
    return memberMapper.convertToDto(req);
  }
}
