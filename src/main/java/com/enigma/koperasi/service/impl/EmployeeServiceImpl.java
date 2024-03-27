package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.employee.UpdateEmployeeReq;
import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.model.entity.Employee;
import com.enigma.koperasi.model.mapper.EmployeeMapper;
import com.enigma.koperasi.model.mapper.PositionMapper;
import com.enigma.koperasi.repository.EmployeeRepository;
import com.enigma.koperasi.service.EmployeeService;
import com.enigma.koperasi.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final PositionService positionService;

  private final EmployeeMapper employeeMapper;
  private final PositionMapper positionMapper;
  @Override
  public EmployeeRes save(Employee req) {
    employeeRepository.store(req);

    return employeeMapper.convertToDto(req);
  }

  @Override
  public Page<EmployeeRes> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<Employee> employees = employeeRepository.findAll(pageable);
    List<EmployeeRes> employeeRes = employees.getContent().stream()
        .map(employeeMapper::convertToDto).toList();

    return new PageImpl<>(employeeRes, pageable, employees.getTotalElements());
  }

  @Override
  public EmployeeRes findByid(String id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(() ->
        new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Employee not found",
            HttpStatus.NOT_FOUND
        ));

    return employeeMapper.convertToDto(employee);
  }

  @Override
  public EmployeeRes update(UpdateEmployeeReq req) {
    EmployeeRes employeeExist = findByid(req.getId());

    PositionReq positionReq = PositionReq.builder()
        .position(req.getPosition())
        .build();
    PositionRes positionRes = positionService.getOrSave(positionReq);

    Employee employee = employeeMapper.convertToEntity(employeeExist);
    employee.setName(req.getName());
    employee.setAddress(req.getAddress());
    employee.setEmail(req.getEmail());
    employee.setPhone(req.getPhone());
    employee.setPosition(positionMapper.convertToEntity(positionRes));
    employee.setUpdatedAt(LocalDateTime.now());
    employeeRepository.store(employee);

    return employeeMapper.convertToDto(employee);
  }

  @Override
  public void delete(String id) {
    EmployeeRes employeeRes = findByid(id);
    employeeRes.setActive(false);

    employeeRepository.store(employeeMapper.convertToEntity(employeeRes));
  }
}
