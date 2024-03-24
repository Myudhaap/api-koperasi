package com.enigma.koperasi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
  private String errorCode;
  private String message;
  private Integer statusCode;
  private String statusName;
  private String path;
  private String method;
}
