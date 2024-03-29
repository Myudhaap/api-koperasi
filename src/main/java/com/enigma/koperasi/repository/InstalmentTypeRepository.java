package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.InstalmentType;
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
public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        INSERT INTO m_instalment_type
        VALUES (
          :#{#instalmentType.id}, 
          :#{#instalmentType.instalmentType.name()}
        )
      """
  )
  void insert(InstalmentType instalmentType);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = """
        UPDATE m_instalment_type
        SET instalment_type = :#{#instalmentType.instalmentType.name()}
        WHERE id = :#{#instalmentType.id}
      """
  )
  void update(InstalmentType instalmentType);

  @Transactional
  default void store(InstalmentType instalmentType){
    if(instalmentType.getId() == null){
      instalmentType.setId(UUID.randomUUID().toString());
      insert(instalmentType);
    }else{
      update(instalmentType);
    }
  }

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM m_instalment_type WHERE id = :id"
  )
  Optional<InstalmentType> findInstalmentTypeById(@NonNull String id);

  @Query(
      nativeQuery = true,
      value = """
        SELECT * FROM m_instalment_type
      """,
      countQuery = """
        SELECT COUNT(*) FROM m_intalment_type
      """
  )
  Page<InstalmentType> findInstalmentTypeAll(Pageable pageable);
}
