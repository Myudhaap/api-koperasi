package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = DbPath.TRX_CASH)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionSaving {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @ManyToOne
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;
  @ManyToOne
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;
  @ManyToOne
  @JoinColumn(name = "type_cash_id")
  private TypeCash typeCash;
  @Column(name = "trx_date")
  private Date trxDate;
  @Check(constraints = "amount > 0")
  private Long amount;
  private String description;
}
