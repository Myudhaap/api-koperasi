package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.constant.EIsActive;
import com.enigma.koperasi.constant.ERole;
import com.enigma.koperasi.constant.EStatus;
import com.enigma.koperasi.model.dto.request.auth.RegisterAuthReq;
import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.request.role.RoleReq;
import com.enigma.koperasi.model.dto.response.auth.RegisterAuthRes;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.model.dto.response.role.RoleRes;
import com.enigma.koperasi.model.entity.*;
import com.enigma.koperasi.model.mapper.CashMapper;
import com.enigma.koperasi.model.mapper.PositionMapper;
import com.enigma.koperasi.model.mapper.RoleMapper;
import com.enigma.koperasi.repository.UserCredentialRepository;
import com.enigma.koperasi.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserCredentialRepository userCredentialRepository;
  private final PositionService positionService;
  private final MemberService memberService;
  private final EmployeeService employeeService;
  private final RoleService roleService;
  private final CashService cashService;

  private final RoleMapper roleMapper;
  private final CashMapper cashMapper;
  private final PositionMapper positionMapper;

  private final PasswordEncoder passwordEncoder;

  @Transactional(rollbackOn = Exception.class)
  @Override
  public RegisterAuthRes registerMember(RegisterAuthReq req) {
    RoleReq roleReq = RoleReq.builder()
        .role(ERole.MEMBER)
        .build();
    RoleRes roleRes = roleService.getOrSave(roleReq);

    CashReq cashReq = CashReq.builder()
        .status(EIsActive.ACTIVE)
        .totalCash(0L)
        .build();
    CashRes cashRes = cashService.save(cashReq);
    System.out.println(cashRes.toString());


    UserCredential userCredential = UserCredential.builder()
        .id(UUID.randomUUID().toString())
        .username(req.getUsername())
        .password(passwordEncoder.encode(req.getPassword()))
        .role(roleMapper.convertToEntity(roleRes))
        .build();
    userCredentialRepository.insertAndFlush(userCredential);

    Member member = Member.builder()
        .name(req.getName())
        .email(req.getEmail())
        .phone(req.getPhone())
        .status(req.getStatus())
        .address(req.getAddress())
        .birthOfDate(req.getBirthOfDate())
        .createdAt(LocalDateTime.now())
        .isActive(true)
        .cash(cashMapper.convertToEntity(cashRes))
        .userCredential(userCredential)
        .build();
    MemberRes memberRes = memberService.save(member);

    return RegisterAuthRes.builder()
        .username(userCredential.getUsername())
        .role(roleRes)
        .build();
  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public RegisterAuthRes registerAdmin(RegisterAuthReq req) {
    RoleReq roleReq = RoleReq.builder()
        .role(ERole.ADMIN)
        .build();
    RoleRes roleRes = roleService.getOrSave(roleReq);

    PositionReq positionReq = PositionReq.builder()
        .position(req.getPosition())
        .build();
    PositionRes positionRes = positionService.getOrSave(positionReq);

    UserCredential userCredential = UserCredential.builder()
        .id(UUID.randomUUID().toString())
        .username(req.getUsername())
        .password(passwordEncoder.encode(req.getPassword()))
        .role(roleMapper.convertToEntity(roleRes))
        .build();
    userCredentialRepository.insertAndFlush(userCredential);

    Employee employee = Employee.builder()
        .name(req.getName())
        .email(req.getEmail())
        .phone(req.getPhone())
        .address(req.getAddress())
        .createdAt(LocalDateTime.now())
        .isActive(true)
        .position(positionMapper.convertToEntity(positionRes))
        .userCredential(userCredential)
        .build();
    EmployeeRes employeeRes = employeeService.save(employee);

    return RegisterAuthRes.builder()
        .username(userCredential.getUsername())
        .role(roleRes)
        .build();
  }
}
