package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CASH_PATH)
public class CashController {
  private final CashService cashService;

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<CashRes> res = cashService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<CashRes>>builder()
            .message("Successfully Get All cash")
            .data(res.getContent())
            .paging(PagingResponse.builder()
                .totalSize(res.getTotalElements())
                .totalPage(res.getTotalPages())
                .currentPage(page)
                .build())
            .build()
        );
  }

  @GetMapping(AppPath.BY_ID)
  public ResponseEntity<?> getById(
      @PathVariable String id
  ){
    CashRes res = cashService.findById(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<CashRes>builder()
            .message("Successfully Get by id cash")
            .data(res)
            .build()
        );
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> update(
    @RequestBody CashReq req
  ){
    CashRes res = cashService.update(req);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<CashRes>builder()
            .message("Successfully update cash")
            .data(res)
            .build()
        );
  }
}
