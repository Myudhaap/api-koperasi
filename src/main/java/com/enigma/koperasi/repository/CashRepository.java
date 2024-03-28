package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.Cash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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

  @Modifying
  @Transactional
  @Query(value = """
  UPDATE m_cash
  SET
    id = :#{#cash.id},
    description = :#{#cash.description},
    total_cash = :#{#cash.totalCash},
    status = :#{#cash.status.name()}
  WHERE id = :#{#cash.id}
""", nativeQuery = true)
  void update(Cash cash);

  @Transactional
  default void storeAndFlush(Cash cash){
    if(cash.getId() == null){
      cash.setId(UUID.randomUUID().toString());
      insert(cash);
    }else{
      update(cash);
    }
    flush();
  }

  Optional<Cash> findCashById(String id);

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM m_cash",
      countQuery = "SELECT COUNT(*) FROM m_cash"
  )
  Page<Cash> findCashAll(Pageable pageable);
}
