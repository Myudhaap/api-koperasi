package com.enigma.koperasi.controller;

import com.enigma.koperasi.constant.AppPath;
import com.enigma.koperasi.model.dto.request.member.MemberReq;
import com.enigma.koperasi.model.dto.request.member.UpdateMemberReq;
import com.enigma.koperasi.model.dto.response.CommonResponse;
import com.enigma.koperasi.model.dto.response.PagingResponse;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.MEMBER_PATH)
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size
  ){
    Page<MemberRes> memberRes = memberService.findAll(page, size);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<MemberRes>>builder()
            .message("Successfully get all member")
            .data(memberRes.getContent())
            .paging(PagingResponse.builder()
                .totalSize(memberRes.getTotalElements())
                .currentPage(page)
                .totalPage(memberRes.getTotalPages())
                .build()
            )
            .build()
        );
  }

  @GetMapping(AppPath.BY_ID)
  public ResponseEntity<?> getById(
      @PathVariable String id
  ){
    MemberRes memberRes = memberService.findById(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<MemberRes>builder()
            .message("Successfully get by id member")
            .data(memberRes)
            .build()
        );
  }

  @PutMapping
  public ResponseEntity<?> update(
      @RequestBody UpdateMemberReq req
      ){
    MemberRes memberRes = memberService.update(req);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<MemberRes>builder()
            .message("Successfully update member")
            .data(memberRes)
            .build()
        );
  }

  @DeleteMapping(AppPath.BY_ID)
  public ResponseEntity<?> delete(
      @PathVariable String id
  ){
    memberService.delete(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<MemberRes>builder()
            .message("Successfully delete member")
            .build()
        );
  }
}
