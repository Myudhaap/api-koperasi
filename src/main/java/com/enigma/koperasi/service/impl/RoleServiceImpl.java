package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.role.RoleReq;
import com.enigma.koperasi.model.dto.response.role.RoleRes;
import com.enigma.koperasi.model.entity.Role;
import com.enigma.koperasi.model.mapper.RoleMapper;
import com.enigma.koperasi.repository.RoleRepository;
import com.enigma.koperasi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  @Override
  public RoleRes getOrSave(RoleReq req) {
    Optional<Role> roleExist = roleRepository.findRoleByName(req.getRole());

    if(roleExist.isEmpty()){
      Role role = roleMapper.convertToEntity(req);
      role.setId(UUID.randomUUID().toString());
      roleRepository.insertAndFlush(role);

      return roleMapper.convertToDto(role);
    }

    return roleMapper.convertToDto(roleExist.get());
  }
}
