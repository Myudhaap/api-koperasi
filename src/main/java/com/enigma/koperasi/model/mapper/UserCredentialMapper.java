package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.response.user_credential.UserCredentialRes;
import com.enigma.koperasi.model.entity.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCredentialMapper {
  private final RoleMapper roleMapper;
  public UserCredential convertToEntity(UserCredentialRes req){
    return UserCredential.builder()
        .id(req.getId())
        .username(req.getUsername())
        .password(req.getPassword())
        .role(roleMapper.convertToEntity(req.getRole()))
        .build();
  }

  public UserCredentialRes convertToDto(UserCredential req){
    return UserCredentialRes.builder()
        .id(req.getId())
        .username(req.getUsername())
        .password(req.getPassword())
        .role(roleMapper.convertToDto(req.getRole()))
        .build();
  }
}
