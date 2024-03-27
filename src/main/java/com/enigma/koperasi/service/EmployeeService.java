package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.employee.EmployeeReq;
import com.enigma.koperasi.model.dto.request.employee.UpdateEmployeeReq;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.entity.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
  EmployeeRes save(Employee req);
  Page<EmployeeRes> findAll(int page, int size);
  EmployeeRes findByid(String id);
  EmployeeRes update(UpdateEmployeeReq req);
  void delete(String id);
}
