package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.ETypeSaving;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = DbPath.TYPE_TRX_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeCash {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "type_cash_name")
  @Enumerated(EnumType.STRING)
  private ETypeSaving typeCashName;

  @OneToMany(mappedBy = "typeCash")
  private List<TransactionSaving> transactionSavings;
}
