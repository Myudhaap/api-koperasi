package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionApproveReq;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionPayReq;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.transaction_loan.TransactionRes;
import com.enigma.koperasi.service.TransactionService;
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
@RequestMapping(AppPath.TRANSACTION)
@SecurityRequirement(name = "Bearer Authentication")
public class TransactionController {
  private final TransactionService transactionService;

  @PreAuthorize("hasAnyRole('MEMBER')")
  @PostMapping
  public ResponseEntity<?> create(
      @RequestBody TransactionReq req
  ){
    TransactionRes res = transactionService.create(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<TransactionRes>builder()
            .message("Successfully created transaction")
            .data(res)
            .build()
        );
  }

  @GetMapping
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<TransactionRes> res = transactionService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<TransactionRes>>builder()
            .message("Successfully get all transaction")
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
    TransactionRes res = transactionService.findById(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<TransactionRes>builder()
            .message("Successfully get by id transaction")
            .data(res)
            .build()
        );
  }

  @PostMapping(AppPath.BY_ID + "/approve")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> approve(
      @PathVariable String id
  ){
    TransactionRes res = transactionService.approve(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<TransactionRes>builder()
            .message("Successfully approve transaction")
            .data(res)
            .build()
        );
  }

  @PostMapping(AppPath.BY_ID + "/reject")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> reject(
      @PathVariable String id
  ){
    TransactionRes res = transactionService.reject(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<TransactionRes>builder()
            .message("Successfully reject transaction")
            .data(res)
            .build()
        );
  }

  @PutMapping("/{trxId}/pay")
  public ResponseEntity<?> pay(
      @RequestBody TransactionPayReq req,
      @PathVariable String trxId
  ){
    TransactionRes res = transactionService.pay(req, trxId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<TransactionRes>builder()
            .message("Successfully pay transaction")
            .data(res)
            .build()
        );
  }
}
