package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
  @Modifying
  @Transactional
  @Query(value = """
    INSERT INTO m_member (id, user_credential_id, cash_id, name, address, email, phone, birth_of_date, status, created_at, updated_at, is_active)
    VALUES (
      :#{#member.id},
      :#{#member.userCredential.id},
      :#{#member.cash.id},
      :#{#member.name},
      :#{#member.address},
      :#{#member.email},
      :#{#member.phone},
      :#{#member.birthOfDate},
      :#{#member.status.name()},
      :#{#member.createdAt},
      :#{#member.updatedAt},
      :#{#member.isActive}
    );
""", nativeQuery = true)
  void insert(Member member);

  @Modifying
  @Transactional
  @Query(value = """
    UPDATE m_member
    SET 
      name = :#{#member.name},
      address = :#{#member.address},
      email = :#{#member.email},
      phone = :#{#member.phone},
      birth_of_date = :#{#member.birthOfDate},
      status = :#{#member.status.name()},
      updated_at = :#{#member.updatedAt},
      is_active = :#{#member.isActive}
    WHERE id = :#{#member.id}
""", nativeQuery = true)
  void update(Member member);

  default void store(Member member){
    if(member.getId() == null){
      member.setId(UUID.randomUUID().toString());
      insert(member);
    }else {
      update(member);
    }
  }

  @Query(
      value = "SELECT * FROM m_member",
      countQuery = "SELECT COUNT(*) FROM m_member",
      nativeQuery = true
  )
  Page<Member> findAll(Pageable pageable);

  @Query(value = "SELECT * FROM m_member WHERE id = :id", nativeQuery = true)
  Optional<Member> findMemberById(@NonNull String id);
}
