package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.constant.ETypeSaving;
import com.enigma.koperasi.model.dto.request.transaction_cash.TransactionCashReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.transaction_cash.TransactionCashRes;
import com.enigma.koperasi.service.TransactionCashService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.TRX_CASH_PATH)
public class TransactionCashController {
  private final TransactionCashService transactionCashService;


  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> transaction(
    @RequestBody TransactionCashReq req
  ){
    TransactionCashRes res = transactionCashService.transaction(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<TransactionCashRes>builder()
            .message("Transaction successfully")
            .data(res)
            .build()
        );
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> findAll(
      @RequestParam(name = "member-name", required = false) String memberName,
      @RequestParam(name = "employee-name", required = false) String employeeName,
      @RequestParam(required = false) Integer amount,
      @RequestParam(name = "type-saving", required = false) ETypeSaving typeSaving,
      @RequestParam(name = "start-date", required = false) Date startDate,
      @RequestParam(name = "end-date", required = false) Date endDate,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
      ){
    Page<TransactionCashRes> res = transactionCashService.findAll(
        employeeName,
        memberName,
        amount,
        typeSaving,
        startDate,
        endDate,
        page,
        size
    );

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<TransactionCashRes>>builder()
            .message("Transaction successfully get all")
            .data(res.getContent())
            .paging(PagingResponse.builder()
                .totalSize(res.getTotalElements())
                .currentPage(page)
                .totalPage(res.getTotalPages())
                .build()
            )
            .build()
        );
  }

  @GetMapping("/member")
  public ResponseEntity<?> findByMember(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
      ){
    Page<TransactionCashRes> res = transactionCashService.findByMember(
        page,
        size
    );

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<TransactionCashRes>>builder()
            .message("Transaction successfully get by id")
            .data(res.getContent())
            .paging(PagingResponse.builder()
                .totalSize(res.getTotalElements())
                .currentPage(page)
                .totalPage(res.getTotalPages())
                .build()
            )
            .build()
        );
  }
}
