package com.enigma.koperasi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponse {
  private Integer currentPage;
  private Integer totalPage;
  private Long totalSize;
}
