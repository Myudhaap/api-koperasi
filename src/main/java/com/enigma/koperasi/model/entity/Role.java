package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = DbPath.ROLE_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "role_name")
  @Enumerated(EnumType.STRING)
  private ERole roleName;

  @OneToMany(mappedBy = "role")
  private List<UserCredential> userCredentials;
}
