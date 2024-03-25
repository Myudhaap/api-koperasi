package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.auth.RegisterAuthReq;
import com.enigma.koperasi.model.dto.response.auth.RegisterAuthRes;

public interface AuthService {
  RegisterAuthRes registerMember(RegisterAuthReq req);
  RegisterAuthRes registerAdmin(RegisterAuthReq req);
}
