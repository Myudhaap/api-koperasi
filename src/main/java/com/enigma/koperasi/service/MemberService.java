package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.request.member.UpdateMemberReq;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.entity.Member;
import org.springframework.data.domain.Page;

public interface MemberService {
  MemberRes save(Member req);
  Page<MemberRes> findAll(int page, int size);
  MemberRes findById(String id);
  MemberRes update(UpdateMemberReq req);
  void delete(String id);
}
