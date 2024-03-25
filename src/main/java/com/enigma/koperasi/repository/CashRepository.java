package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CashRepository extends JpaRepository<Cash, String> {
  @Modifying
  @Transactional
  @Query(value = """
  INSERT INTO m_cash(id, description, total_cash, status)
  VALUES (
    :#{#cash.id},
    :#{#cash.description},
    :#{#cash.totalCash},
    :#{#cash.status.name()}
  );
""", nativeQuery = true)
  void insert(Cash cash);

  default void insertAndFlush(Cash cash){
    insert(cash);
    flush();
  }
}
