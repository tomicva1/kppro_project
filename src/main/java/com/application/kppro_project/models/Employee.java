package com.application.kppro_project.models;

import com.application.kppro_project.dao.DepartmentRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "employee")
public class Employee {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Long departmentId;
    @JsonIgnore
    private String token;
    private String email;
    private String mobile;

    public Employee() {}

    public Employee(String firstName, String lastName, String username, String role, Long departmentId) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.departmentId = departmentId;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", departmentId=" + departmentId +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toStringLogin(String expiration){
        return '{' +
                "\"userId\":" + id +
                ", \"token\":\"" + token + '"' +
                ", \"expiration\":\"" + expiration + '"' +
                '}';
    }

    public String toJson(String department) {
        return "{\"data\":{" +
                    "\"firstName\":\"" + firstName + "\"," +
                    "\"lastName\":\"" + lastName + "\"," +
                    "\"role\":\"" + role + "\"," +
                    "\"department\":\"" + department + "\"}}";
    }
}