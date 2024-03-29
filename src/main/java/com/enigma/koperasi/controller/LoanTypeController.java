package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.loan.LoanTypeReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.loan.LoanTypeRes;
import com.enigma.koperasi.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.LOANTYPE)
public class LoanTypeController {
  private final LoanTypeService loanTypeService;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @PostMapping
  public ResponseEntity<?> create(
      @RequestBody LoanTypeReq req
  ){
    LoanTypeRes res = loanTypeService.create(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<LoanTypeRes>builder()
            .message("Successfully created loan type")
            .data(res)
            .build()
        );
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
  @PutMapping
  public ResponseEntity<?> update(
      @RequestBody LoanTypeReq req
  ){
    LoanTypeRes res = loanTypeService.update(req);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<LoanTypeRes>builder()
            .message("Successfully updated loan type")
            .data(res)
            .build()
        );
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
  @DeleteMapping(AppPath.BY_ID)
  public ResponseEntity<?> delete(
      @PathVariable String id
  ){
    loanTypeService.delete(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<LoanTypeRes>builder()
            .message("Successfully deleted loan type")
            .build()
        );
  }

  @GetMapping
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<LoanTypeRes> res = loanTypeService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<List<LoanTypeRes>>builder()
            .message("Successfully get all loan type")
            .data(res.getContent())
            .paging(PagingResponse.builder()
                .currentPage(page)
                .totalPage(res.getTotalPages())
                .totalSize(res.getTotalElements())
                .build())
            .build()
        );
  }

  @GetMapping(AppPath.BY_ID)
  public ResponseEntity<?> getById(
      @PathVariable String id
  ){
    LoanTypeRes res = loanTypeService.findById(id);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<LoanTypeRes>builder()
            .message("Successfully get by id loan type")
            .data(res)
            .build()
        );
  }
}
