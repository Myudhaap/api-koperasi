package com.enigma.koperasi.model.entity;

import com.enigma.koperasi.constant.DbPath;
import com.enigma.koperasi.constant.EPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = DbPath.POSITION_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "position_name")
  private EPosition positionName;

  @OneToMany(mappedBy = "position")
  private List<Employee> employees;
}
