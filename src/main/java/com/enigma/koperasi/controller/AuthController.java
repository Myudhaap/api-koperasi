package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.auth.LoginAuthReq;
import com.enigma.koperasi.model.dto.request.auth.RegisterAuthReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.auth.LoginAuthRes;
import com.enigma.koperasi.model.dto.response.auth.RegisterAuthRes;
import com.enigma.koperasi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppPath.AUTH_PATH)
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register/member")
  public ResponseEntity<?> registerMember(
    @RequestBody RegisterAuthReq req
  ){
    RegisterAuthRes res = authService.registerMember(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<RegisterAuthRes>builder()
            .message("Successfully register")
            .data(res)
            .build()
        );
  }

  @PostMapping("/register/admin")
  public ResponseEntity<?> registerAdmin(
      @RequestBody RegisterAuthReq req
  ){
    RegisterAuthRes res = authService.registerAdmin(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<RegisterAuthRes>builder()
            .message("Successfully register")
            .data(res)
            .build()
        );
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestBody LoginAuthReq req
  ){
    LoginAuthRes res = authService.login(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<LoginAuthRes>builder()
            .message("Successfully login")
            .data(res)
            .build()
        );
  }
}
