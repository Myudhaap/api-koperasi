package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.response.user_credential.UserCredentialRes;
import com.enigma.koperasi.model.entity.AppUser;
import com.enigma.koperasi.model.entity.UserCredential;
import com.enigma.koperasi.model.mapper.RoleMapper;
import com.enigma.koperasi.repository.UserCredentialRepository;
import com.enigma.koperasi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserCredentialRepository userCredentialRepository;
  private final RoleMapper roleMapper;
  @Override
  public UserCredentialRes findById(String id) {
    Optional<UserCredential> userCredential = userCredentialRepository.findUserById(id);
    if(userCredential.isEmpty()) throw new ApplicationException(
        HttpStatus.NOT_FOUND.name(),
        "User not found",
        HttpStatus.NOT_FOUND
    );

    return UserCredentialRes.builder()
        .id(userCredential.get().getId())
        .username(userCredential.get().getUsername())
        .password(userCredential.get().getPassword())
        .role(roleMapper.convertToDto(userCredential.get().getRole()))
        .build();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserCredential> userCredential = userCredentialRepository.findByUsername(username);
    if(userCredential.isEmpty()) throw new ApplicationException(
        HttpStatus.NOT_FOUND.name(),
        "User not found!",
        HttpStatus.NOT_FOUND
    );

    return AppUser.builder()
        .id(userCredential.get().getId())
        .username(userCredential.get().getUsername())
        .password(userCredential.get().getPassword())
        .role(userCredential.get().getRole().getRoleName())
        .build();
  }

  @Override
  public UserDetails loadById(String id) {
    Optional<UserCredential> userCredential = userCredentialRepository.findUserById(id);
    if(userCredential.isEmpty()) throw new ApplicationException(
        HttpStatus.NOT_FOUND.name(),
        "User not found!",
        HttpStatus.NOT_FOUND
    );

    return AppUser.builder()
        .id(userCredential.get().getId())
        .username(userCredential.get().getUsername())
        .password(userCredential.get().getPassword())
        .role(userCredential.get().getRole().getRoleName())
        .build();
  }
}
