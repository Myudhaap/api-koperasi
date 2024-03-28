package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.request.member.UpdateMemberReq;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.entity.Employee;
import com.enigma.koperasi.model.entity.Member;
import com.enigma.koperasi.model.mapper.MemberMapper;
import com.enigma.koperasi.repository.MemberRepository;
import com.enigma.koperasi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;
  @Override
  public MemberRes save(Member req) {
    memberRepository.store(req);
    return memberMapper.convertToDto(req);
  }

  @Override
  public Page<MemberRes> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<Member> members = memberRepository.findAll(pageable);

    List<MemberRes> memberResList = members.getContent().stream()
        .map(memberMapper::convertToDto).toList();

    return new PageImpl<>(memberResList, pageable, members.getTotalElements());
  }

  @Override
  public MemberRes findById(String id) {
    Member member = memberRepository.findMemberById(id).orElseThrow(() ->
        new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Member not found",
            HttpStatus.NOT_FOUND
        ));

    return memberMapper.convertToDto(member);
  }

  @Override
  public MemberRes findByUserCredentialId(String userId) {
    Member member = memberRepository.findByUserCredentialId(userId).orElseThrow(() ->
        new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Member not found",
            HttpStatus.NOT_FOUND
        ));

    return memberMapper.convertToDto(member);
  }

  @Override
  public MemberRes update(UpdateMemberReq req) {
    MemberRes memberExist = findById(req.getId());

    Member member = memberMapper.convertToEntity(memberExist);
    member.setName(req.getName());
    member.setAddress(req.getAddress());
    member.setStatus(req.getStatus());
    member.setBirthOfDate(req.getBirthOfDate());
    member.setEmail(req.getEmail());
    member.setPhone(req.getPhone());
    member.setUpdatedAt(LocalDateTime.now());
    memberRepository.store(member);

    System.out.println(member.getCash());

    return memberMapper.convertToDto(member);
  }

  @Override
  public void delete(String id) {
    MemberRes memberExist = findById(id);

    Member member = memberMapper.convertToEntity(memberExist);
    member.setActive(false);
    memberRepository.store(member);
  }
}
