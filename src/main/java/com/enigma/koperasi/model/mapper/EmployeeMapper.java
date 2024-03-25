package com.enigma.koperasi.model.mapper;

import com.enigma.koperasi.model.dto.request.employee.EmployeeReq;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.entity.Employee;
import com.enigma.koperasi.model.entity.Position;
import com.enigma.koperasi.model.entity.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
  private final PositionMapper positionMapper;
  private final UserCredentialMapper userCredentialMapper;
  public  Employee convertToEntity(EmployeeReq req, UserCredential userCredential, Position position){
    return Employee.builder()
        .id(req.getId())
        .userCredential(userCredential)
        .position(position)
        .name(req.getName())
        .address(req.getAddress())
        .email(req.getEmail())
        .phone(req.getPhone())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .isActive(req.isActive())
        .build();
  }

  public  EmployeeRes convertToDto(Employee req){
    return EmployeeRes.builder()
        .id(req.getId())
        .userCredential(userCredentialMapper.convertToDto(req.getUserCredential()))
        .position(positionMapper.convertToDto(req.getPosition()))
        .name(req.getName())
        .address(req.getAddress())
        .email(req.getEmail())
        .phone(req.getPhone())
        .createdAt(req.getCreatedAt())
        .updatedAt(req.getUpdatedAt())
        .isActive(req.isActive())
        .build();
  }
}
