package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.auth.LoginAuthReq;
import com.enigma.koperasi.model.dto.request.auth.RegisterAuthReq;
import com.enigma.koperasi.model.dto.response.auth.LoginAuthRes;
import com.enigma.koperasi.model.dto.response.auth.RegisterAuthRes;

public interface AuthService {
  RegisterAuthRes registerMember(RegisterAuthReq req);
  RegisterAuthRes registerAdmin(RegisterAuthReq req);
  LoginAuthRes login(LoginAuthReq req);

}
