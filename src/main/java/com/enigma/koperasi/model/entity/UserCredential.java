package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = DbPath.USER_CREDENTIAL_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredential {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @ManyToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;
  @Column(length = 100, nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;

  @OneToOne(mappedBy = "userCredential")
  private Member member;
  @OneToOne(mappedBy = "userCredential")
  private Employee employee;
}
