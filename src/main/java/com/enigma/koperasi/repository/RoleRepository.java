package com.enigma.koperasi.repository;

import com.enigma.koperasi.constant.ERole;
import com.enigma.koperasi.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  @Modifying
  @Query(value = """
    INSERT INTO m_role
    VALUES (
      :#{#role.id},
      :#{#role.roleName.name()}
    );
""", nativeQuery = true)
  void insert(Role role);

  @Transactional
  default void insertAndFlush(Role role){
    insert(role);
    flush();
  }

  @Query(value = "SELECT * FROM m_role WHERE id = :id", nativeQuery = true)
  Role findRoleById(String id);

  @Query(value = "SELECT * FROM m_role WHERE role_name = :#{#name.name()}", nativeQuery = true)
  Optional<Role> findRoleByName(ERole name);
}
