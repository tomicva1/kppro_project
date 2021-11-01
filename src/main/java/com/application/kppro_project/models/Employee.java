package com.application.kppro_project.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Trida objektu Contractor
 */

@Entity
@Table(name = "employee")
public class Employee extends User {

    @Column(name = "hire_date")
    @NotNull(message = "Set hire date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    @Column(name = "address")
    @NotEmpty(message = "Set contractors address")
    private String address;

    @Column(name = "city")
    @NotEmpty(message = "Set contractors city")
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Min(value = 100000000, message = "Set real phone number, format: xxxxxxxxx")
    @Max(value = 999999999)
    private String telephone;

    // constructors
    public Employee() {
    }

    public Employee(@NotNull LocalDate hireDate, @NotEmpty(message = "Set contractors address") String address, @NotEmpty(message = "Set contractors address") String city, @Min(value = 100000000, message = "Set real phone number") @Max(value = 999999999, message = "Set real phone number") String telephone) {
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    @Transient
    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
