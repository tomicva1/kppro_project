package com.application.kppro_project.services;

import com.application.kppro_project.configurations.MyUser;
import com.application.kppro_project.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService personService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional <Employee> optionalEmployee = Optional.ofNullable(personService.findEmployeeByUsername(userName));
        if(optionalEmployee.isPresent()) {
            return new  MyUser(optionalEmployee.get().getUsername(), optionalEmployee.get().getPassword(), optionalEmployee.get().getRole() ,optionalEmployee.get().getId());
        }
        return null;
    }
}
