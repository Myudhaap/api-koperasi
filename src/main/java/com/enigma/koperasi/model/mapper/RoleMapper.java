package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.role.RoleReq;
import com.enigma.koperasi.model.dto.response.role.RoleRes;
import com.enigma.koperasi.model.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
  public  Role convertToEntity(RoleReq req) {
    return Role.builder()
        .id(req.getId())
        .roleName(req.getRole())
        .build();
  }

  public  Role convertToEntity(RoleRes req) {
    return Role.builder()
        .id(req.getId())
        .roleName(req.getRole())
        .build();
  }

  public  RoleRes convertToDto(Role req) {
    return RoleRes.builder()
        .id(req.getId())
        .role(req.getRoleName())
        .build();
  }
}
