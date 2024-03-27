package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.Employee;
import com.enigma.koperasi.model.entity.Member;
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
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  @Modifying
  @Transactional
  @Query(value = """
    INSERT INTO m_employee (id, position_id, user_credential_id, name, address, email, phone, created_at, updated_at, is_active)
    VALUES (
      :#{#employee.id},
      :#{#employee.position.id},
      :#{#employee.userCredential.id},
      :#{#employee.name},
      :#{#employee.address},
      :#{#employee.email},
      :#{#employee.phone},
      :#{#employee.createdAt},
      :#{#employee.updatedAt},
      :#{#employee.isActive}
    );
""", nativeQuery = true)
  void insert(Employee employee);

  @Modifying
  @Transactional
  @Query(value = """
    UPDATE m_employee
    SET
      name = :#{#employee.name},
      address = :#{#employee.address},
      email = :#{#employee.email},
      phone = :#{#employee.phone},
      updated_at = :#{#employee.updatedAt},
      is_active = :#{#employee.isActive},
      position_id = :#{#employee.position.id}
    WHERE id = :#{#employee.id}
""", nativeQuery = true)
  void update(Employee employee);

  default void store(Employee employee){
    if(employee.getId() == null){
      employee.setId(UUID.randomUUID().toString());
      insert(employee);
    }else{
      update(employee);
    }
  }

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM m_employee",
      countQuery = "SELECT COUNT(*) FROM m_employee"
  )
  Page<Employee> findAll(Pageable pageable);

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM m_employee WHERE id = :id"
  )
  Optional<Employee> findById(String id);
}
