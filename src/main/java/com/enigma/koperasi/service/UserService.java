package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.response.user_credential.UserCredentialRes;
import com.enigma.koperasi.model.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserCredentialRes findById(String id);
}
