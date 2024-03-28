package com.enigma.koperasi.repository;

import com.enigma.koperasi.constant.EPosition;
import com.enigma.koperasi.model.entity.Cash;
import com.enigma.koperasi.model.entity.Position;
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
public interface PositionRepository extends JpaRepository<Position, String> {
  @Modifying
  @Transactional
  @Query(value = """
    INSERT INTO m_position(id, position_name, is_active)
    VALUES (
      :#{#position.id}, 
      :#{#position.positionName.name()},
      :#{#position.isActive}
    );
""", nativeQuery = true)
  void insert(Position position);

  @Modifying
  @Transactional
  @Query(value = """
  UPDATE m_position
  SET
    id = :#{#position.id},
    position_name = :#{#position.positionName.name()},
    is_active = :#{#position.isActive}
  WHERE id = :#{#position.id}
""", nativeQuery = true)
  void update(Position position);

  @Transactional
  default void storeAndFlush(Position position){
    if(position.getId() == null){
      position.setId(UUID.randomUUID().toString());
      insert(position);
    }else{
      update(position);
    }
    flush();
  }

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM m_position WHERE is_active = true",
      countQuery = "SELECT COUNT(*) FROM m_position WHERE is_active = true"
  )
  Page<Position> findPositionAll(Pageable pageable);

  @Query(value = "SELECT * FROM m_position WHERE id = :id AND is_active = true", nativeQuery = true)
  Optional<Position> findPositionById(String id);

  @Query(value = "SELECT * FROM m_position WHERE position_name = :#{#position.name()} AND is_active = true", nativeQuery = true)
  Optional<Position> findPositionByName(EPosition position);
}
