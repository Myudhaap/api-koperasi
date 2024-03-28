package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.position.PositionReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.position.PositionRes;
import com.enigma.koperasi.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.POSITION_PATH)
public class PositionController {
  private final PositionService positionService;

  @GetMapping
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<PositionRes> res = positionService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<PositionRes>>builder()
            .message("Successfully Get All position")
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
    PositionRes res = positionService.findById(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<PositionRes>builder()
            .message("Successfully Get by id position")
            .data(res)
            .build()
        );
  }

  @PutMapping
  public ResponseEntity<?> update(
      @RequestBody PositionReq req
  ){
    PositionRes res = positionService.update(req);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<PositionRes>builder()
            .message("Successfully update position")
            .data(res)
            .build()
        );
  }

  @DeleteMapping(AppPath.BY_ID)
  public ResponseEntity<?> delete(
      @PathVariable String id
  ){
    positionService.delete(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<PositionRes>builder()
            .message("Successfully delete position")
            .build()
        );
  }
}
