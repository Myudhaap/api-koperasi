package com.enigma.koperasi.repository;

import com.enigma.koperasi.constant.ERole;
import com.enigma.koperasi.constant.ETypeSaving;
import com.enigma.koperasi.model.entity.Cash;
import com.enigma.koperasi.model.entity.Role;
import com.enigma.koperasi.model.entity.TypeCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeCashRespository extends JpaRepository<TypeCash, String> {
  @Modifying
  @Transactional
  @Query(value = """
  INSERT INTO m_type_trx_cash(id, type_cash_name)
  VALUES (
    :#{#typeCash.id},
    :#{#typeCash.typeCashName.name()}
  );
""", nativeQuery = true)
  void insert(TypeCash typeCash);

  @Transactional
  default void insertAndFlush(TypeCash typeCash){
    typeCash.setId(UUID.randomUUID().toString());
    insert(typeCash);
    flush();
  }

  @Query(value = "SELECT * FROM m_type_trx_cash WHERE type_cash_name = :#{#name.name()}", nativeQuery = true)
  Optional<TypeCash> findTypeCashByName(ETypeSaving name);
}
