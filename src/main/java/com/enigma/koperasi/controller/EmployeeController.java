package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.employee.UpdateEmployeeReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.EMPLOYEE_PATH)
@SecurityRequirement(name = "Bearer Authentication")
public class EmployeeController {
  private final EmployeeService employeeService;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<EmployeeRes> res = employeeService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<EmployeeRes>>builder()
            .message("Successfully get all employee")
            .data(res.getContent())
            .paging(PagingResponse.builder()
                .totalPage(res.getTotalPages())
                .currentPage(page)
                .totalSize(res.getTotalElements())
                .build())
            .build()
        );
  }

  @GetMapping(AppPath.BY_ID)
  public ResponseEntity<?> getById(
      @PathVariable String id
  ){
    EmployeeRes res = employeeService.findByid(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<EmployeeRes>builder()
            .message("Successfully get by id employee")
            .data(res)
            .build()
        );
  }

  @PutMapping
  public ResponseEntity<?> update(
      @RequestBody UpdateEmployeeReq req
      ){
    EmployeeRes res = employeeService.update(req);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<EmployeeRes>builder()
            .message("Successfully update employee")
            .data(res)
            .build()
        );
  }

  @DeleteMapping(AppPath.BY_ID)
  public ResponseEntity<?> delete(
      @PathVariable String id
  ){
    employeeService.delete(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<EmployeeRes>builder()
            .message("Successfully delete employee")
            .build()
        );
  }
}
