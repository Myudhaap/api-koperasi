package com.enigma.koperasi.repository;

import com.enigma.koperasi.model.entity.Employee;
import com.enigma.koperasi.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  @Modifying
  @Transactional
  @Query(value = """
    INSERT INTO m_employee (id, name, address, email, phone, created_at, updated_at, is_active)
    VALUES (
      :#{#employee.id},
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
}
