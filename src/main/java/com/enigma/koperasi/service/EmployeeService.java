package com.enigma.koperasi.service;

import com.enigma.koperasi.model.dto.request.employee.EmployeeReq;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.entity.Employee;

public interface EmployeeService {
  EmployeeRes save(Employee req);
}
