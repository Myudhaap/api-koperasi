package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = DbPath.LOAN_TYPE_SCHEMA)
public class LoanType {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "maximum_loan")
  private Double maxLoan;
  @Check(constraints = "interest >= 0 AND interest <= 100")
  private Integer interest;
  private String type;
  @Column(name = "is_active")
  @Builder.Default
  private boolean isActive = true;

  @OneToMany(mappedBy = "loanType")
  private List<LoanTransaction> loanTransactions;
}
