package com.application.kppro_project.models;

import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vacation")
public class Vacation extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "start_date")
    @NotNull(message = "Set start date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull(message = "Set End date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @Column(name = "approved")
    @NotNull(message = "Set approval state")
    private Boolean approvalState;

    @Column(name = "approvedBy")
    private Employee manager;

    @Column(name = "approval_time")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate approvalTime;

    public Vacation(){

    }

    public Vacation(Employee employee, @NotNull(message = "Set start date") LocalDate startDate, @NotNull(message = "Set End date") LocalDate endDate, @NotNull(message = "Set approval state") Boolean approvalState, Employee manager, LocalDate approvalTime) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approvalState = approvalState;
        this.manager = manager;
        this.approvalTime = approvalTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(Boolean approvalState) {
        this.approvalState = approvalState;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public LocalDate getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDate approvalTime) {
        this.approvalTime = approvalTime;
    }
}
