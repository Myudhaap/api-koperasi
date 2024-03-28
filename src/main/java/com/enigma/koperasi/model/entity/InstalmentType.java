package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.EInstalmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = DbPath.INSTALMENT_TYPE_SCHEMA)
public class InstalmentType {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Enumerated(EnumType.STRING)
  @Column(name = "instalment_type", unique = true)
  private EInstalmentType instalmentType;

  @OneToMany(mappedBy = "instalmentType")
  private List<LoanTransaction> loanTransactions;
}
