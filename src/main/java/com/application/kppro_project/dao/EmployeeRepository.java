package com.application.kppro_project.dao;

import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Object> findByUsername(String username);

    Optional<Object> findByToken(String token);
}
