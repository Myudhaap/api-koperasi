package com.enigma.koperasi.model.dto.response.instalment;

import com.enigma.koperasi.constant.EInstalmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeRes {
  private String id;
  private EInstalmentType instalmentType;
}
