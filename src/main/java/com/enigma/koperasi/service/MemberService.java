package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.entity.Member;

public interface MemberService {
  MemberRes save(Member req);
}
