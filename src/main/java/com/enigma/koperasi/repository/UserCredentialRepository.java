package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
  @Modifying
  @Transactional
  @Query(value = """
    INSERT INTO m_user_credential (id, role_id, username, password)
    VALUES (
      :#{#userCredential.id}, 
      :#{#userCredential.role.id},
      :#{#userCredential.username},
      :#{#userCredential.password}
    );
""", nativeQuery = true)
  void insert(UserCredential userCredential);

  @Transactional
  default void insertAndFlush(UserCredential userCredential){
    insert(userCredential);
    flush();
  }

  @Query(value = "SELECT * FROM m_user_credential WHERE id = :id", nativeQuery = true)
  Optional<UserCredential> findUserById(String id);

  @Query(value = "SELECT * FROM m_user_credential WHERE username = :username", nativeQuery = true)
  Optional<UserCredential> findByUsername(String username);
}
