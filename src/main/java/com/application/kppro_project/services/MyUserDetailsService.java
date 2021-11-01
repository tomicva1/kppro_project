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
        Optional <Employee> optionalContractor = Optional.ofNullable(personService.findEmployeeByUsername(userName));
        if(optionalContractor.isPresent()) {
            return new  MyUser(optionalContractor.get().getUsername(), optionalContractor.get().getPassword(), optionalContractor.get().getRole() ,optionalContractor.get().getId());
        }
        return null;
    }
}
