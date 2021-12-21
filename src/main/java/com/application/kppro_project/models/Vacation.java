package com.application.kppro_project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vacation")
public class Vacation {

    private @Id @GeneratedValue Long id;
    private Long employee_id;
    private Date dateFrom;
    private Date dateTo;
    private String note;
    private boolean approved;
    private Long approvedBy;
    private Date approvalTime;

    public Vacation() {
    }

    public Vacation(Long id, Long employee_id, Date dateFrom, Date dateTo, String note, boolean approved) {
        this.id = id;
        this.employee_id = employee_id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
        this.approved = approved;
    }

    public Vacation(Long id, boolean approved, Long approvedBy, Date approvalTime) {
        this.id = id;
        this.approved = approved;
        this.approvedBy = approvedBy;
        this.approvalTime = approvalTime;
    }

    public Long getId() {
        return this.id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public void setVacation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return approved == vacation.approved &&
                Objects.equals(id, vacation.id) &&
                Objects.equals(employee_id, vacation.employee_id) &&
                Objects.equals(dateFrom, vacation.dateFrom) &&
                Objects.equals(dateTo, vacation.dateTo) &&
                Objects.equals(note, vacation.note) &&
                Objects.equals(approvedBy, vacation.approvedBy) &&
                Objects.equals(approvalTime, vacation.approvalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee_id, dateFrom, dateTo, note, approved, approvedBy, approvalTime);
    }

    @Override
    public String toString() {
        return "Vacation{" + "id=" + this.id + ", employee_id='" + this.employee_id + '\'' + ", dateFrom='" + this.dateFrom
                + '\'' + ", dateTo='" + this.dateTo + '\'' + ", approved='" + this.approved + '\'' + ", approvedBy='" + this.approvedBy
                + '\'' + ", approvalTime='" + this.approvalTime + '\'' + ", note='" + this.note + '\'' + '}';
    }
}
