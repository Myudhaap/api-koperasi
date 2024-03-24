package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = DbPath.MEMBER_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @OneToOne
  @JoinColumn(name = "user_credential_id", referencedColumnName = "id")
  private UserCredential userCredential;
  @OneToOne
  @JoinColumn(name = "cash_id", referencedColumnName = "id")
  private Cash cash;
  @Column(length = 100, nullable = false)
  private String name;
  @Column(nullable = false)
  private String address;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false, length = 20)
  private String phone;
  @Column(name = "birth_of_date", nullable = false)
  private Date birthOfDate;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private EStatus status;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
  @Column(name = "is_active")
  @Builder.Default
  private boolean isActive = true;

  @OneToMany(mappedBy = "member")
  private List<TransactionSaving> transactionSavings;
}
