package com.enigma.koperasi.repository;

import com.enigma.koperasi.constant.EPosition;
import com.enigma.koperasi.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
  @Modifying
  @Transactional
  @Query(value = """
    INSERT INTO m_position
    VALUES (
      :#{#position.id}, 
      :#{#position.positionName.name()} 
    );
""", nativeQuery = true)
  void insert(Position position);

  @Transactional
  default void insertAndFlush(Position position){
    insert(position);
    flush();
  }

  @Query(value = "SELECT * FROM m_position WHERE id = :id", nativeQuery = true)
  Optional<Position> findPositionById(String id);

  @Query(value = "SELECT * FROM m_position WHERE position_name = :#{#position.name()}", nativeQuery = true)
  Optional<Position> findPositionByName(EPosition position);
}
