package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.role.RoleReq;
import com.enigma.koperasi.model.dto.response.role.RoleRes;

public interface RoleService {
  RoleRes getOrSave(RoleReq req);
}
