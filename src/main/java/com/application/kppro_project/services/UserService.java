package com.application.kppro_project.services;

import com.application.kppro_project.dao.EmployeeRepo;
import com.application.kppro_project.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Collection<Employee> findAllEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        for (Employee employee :employeeRepo.findAll())
        {
            employees.add(employee);
        }
        return employees;
    }

    public Collection<Employee> findEmployeesByFirstNameLastName(String name){
        List<Employee> employees = new ArrayList<Employee>();
        for (Employee employee :employeeRepo.findEmployeesByName(name))
        {
            employees.add(employee);
        }
        return employees;
    }

    public Employee findEmployeeByUsername(String username){
        Employee employee = employeeRepo.findEmployeeByUsername(username);
        return employee;
    }

    public boolean isUnique(String username){
        boolean unique = true;

        if(findEmployeeByUsername(username) != null)
            unique = false;

        return unique;

    }

    public void deleteEmployee(int id){
        employeeRepo.deleteById(id);
    }

    public void saveEmployee(Employee c){
        employeeRepo.save(c);
    }

    public Employee getEmployee(int id){
        return employeeRepo.findById(id).get();
    }

}
