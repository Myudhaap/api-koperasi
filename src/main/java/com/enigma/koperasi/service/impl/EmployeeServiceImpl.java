package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.model.dto.request.employee.EmployeeReq;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.entity.Employee;
import com.enigma.koperasi.model.mapper.EmployeeMapper;
import com.enigma.koperasi.repository.EmployeeRepository;
import com.enigma.koperasi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;
  @Override
  public EmployeeRes save(Employee req) {
    req.setId(UUID.randomUUID().toString());

    employeeRepository.save(req);
    return employeeMapper.convertToDto(req);
  }
}
