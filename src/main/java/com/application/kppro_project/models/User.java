package com.application.kppro_project.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Ken Krebs
 */
@MappedSuperclass
public class User extends BaseEntity {

    @Column(name = "first_name")
    @Pattern(regexp="^[a-zA-Z]{3,20}",message="Invalid name type")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp="^[a-zA-Z]{3,20}",message="Invalid name type")
    private String lastName;

    @Column(name = "username")
    @Pattern(regexp="^[a-zA-Z0-9]{3,20}",message="Invalid name type")
    private String username;

    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
