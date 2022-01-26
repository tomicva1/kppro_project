package com.application.kppro_project.controllers;


import com.application.kppro_project.dao.EmployeeRepository;
import com.application.kppro_project.models.Employee;
import com.application.kppro_project.models.EmployeeModelAssembler;
import com.application.kppro_project.other.Exception;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EmployeeController {

    public final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/login")
    public String login() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        Employee employee = (Employee) repository.findByUsername(principal) //
                .orElseThrow(() -> new Exception());


        System.out.println("Principal: " + principal);

        List<String> response = getJWTToken(principal);
        String token = response.get(0);
        String expiration = response.get(1);

        employee.setToken(token);

        repository.save(employee);
        return employee.toStringLogin(expiration);
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    public List<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return employees;
    }

    // Single item
    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new Exception());
        //.orElseThrow(() -> new Exception("Employee with this id: " + id + " not exist"));

        return assembler.toModel(employee);
    }


    // end::get-aggregate-root[]
    @PostMapping("/employees")
    public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
        newEmployee.setPassword(pwdCreate(newEmployee.getPassword()));
        repository.save(newEmployee);
        EntityModel<Employee> entityModel = assembler.toModel(newEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);

        //return "User has been deleted";
    }


    private static List<String> getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        Date expiration = new Date(System.currentTimeMillis() + 600000);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        List<String> response = new ArrayList<>();
        response.add(token);
        response.add(expiration.toString());
        return response;
    }

    private static String pwdCreate(String userPwd){
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.encode(userPwd);
    }

    private static boolean pwdMatch(String userPwd, String pwd){
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        return b.matches(pwd, userPwd);
    }

    /*public boolean checkToken(String token){
        repository.findByToken(token) //
                .orElseThrow(() -> new NotFoundException("token"));

        return true;
    }*/
}