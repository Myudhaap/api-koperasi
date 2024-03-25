package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
