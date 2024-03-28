package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.EApprovalStatus;
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
@Entity(name = DbPath.TRANSACTION_SCHEMA)
public class LoanTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @ManyToOne
  @JoinColumn(name = "instalment_type_id", referencedColumnName = "id")
  private InstalmentType instalmentType;
  @ManyToOne
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;
  @ManyToOne
  @JoinColumn(name = "loan_type_id", referencedColumnName = "id")
  private LoanType loanType;
  private Double nominal;
  @Column(name = "approved_at")
  private Long approvedAt;
  @ManyToOne
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;
  @Column(name = "approval_status")
  @Enumerated(EnumType.STRING)
  private EApprovalStatus approvalStatus;
  @OneToMany(mappedBy = "loanTransaction", cascade = CascadeType.ALL)
  private List<LoanTransactionDetail> loanTransactionDetails;
  @Column(name = "created_at")
  private Long createdAt;
  @Column(name = "updated_at")
  private Long updatedAt;
}
