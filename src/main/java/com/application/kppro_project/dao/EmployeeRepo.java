package com.application.kppro_project.dao;

import com.application.kppro_project.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepo  extends CrudRepository <Employee, Integer>
{
    Employee findEmployeeByUsername(String username);

    @Query(
            value = "SELECT * FROM employee WHERE CONCAT(first_name, ' ', last_name) LIKE CONCAT('%',?1,'%')",
            nativeQuery = true)
    List<Employee> findEmployeesByName(String name);

}
