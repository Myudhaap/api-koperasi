package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.instalment.InstalmentTypeReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.instalment.InstalmentTypeRes;
import com.enigma.koperasi.service.InstalmentTypeService;
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
@RequestMapping(AppPath.INSTALMENTYPE)
@SecurityRequirement(name = "Bearer Authentication")
public class InstalmentTypeController {
  private final InstalmentTypeService instalmentTypeService;

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> create(
      @RequestBody InstalmentTypeReq req
  ){
    InstalmentTypeRes res = instalmentTypeService.create(req);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<InstalmentTypeRes>builder()
            .message("Successfully created Instalment type")
            .data(res)
            .build()
        );
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
  public ResponseEntity<?> update(
      @RequestBody InstalmentTypeReq req
  ){
    InstalmentTypeRes res = instalmentTypeService.update(req);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<InstalmentTypeRes>builder()
            .message("Successfully updated Instalment type")
            .data(res)
            .build()
        );
  }

  @DeleteMapping(AppPath.BY_ID)
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
  public ResponseEntity<?> delete(
      @PathVariable String id
  ){
    instalmentTypeService.delete(id);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<InstalmentTypeRes>builder()
            .message("Successfully deleted Instalment type")
            .build()
        );
  }

  @GetMapping
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<InstalmentTypeRes> res = instalmentTypeService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<List<InstalmentTypeRes>>builder()
            .message("Successfully get all Instalment type")
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
    InstalmentTypeRes res = instalmentTypeService.findById(id);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CommonResponse.<InstalmentTypeRes>builder()
            .message("Successfully get by id Instalment type")
            .data(res)
            .build()
        );
  }
}
