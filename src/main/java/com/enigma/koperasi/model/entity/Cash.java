package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.EIsActive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity(name = DbPath.CASH_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cash {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "total_cash", nullable = false)
  @Check(constraints = "total_cash >= 0")
  private Long totalCash;
  private String description;
  @Enumerated(EnumType.STRING)
  private EIsActive status;

  @OneToOne(mappedBy = "cash")
  private Member member;
}
