package com.enigma.koperasi.model.dto.request.instalment;

import com.enigma.koperasi.constant.EInstalmentType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeReq {
  private String id;
  @Column(name = "instalment_type")
  private EInstalmentType instalmentType;
  @Column(name = "is_active")
  @Builder.Default
  private boolean isActive = true;
}
