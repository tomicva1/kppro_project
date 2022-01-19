package com.application.kppro_project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
//@Table(name = "vacation")
public class Vacation {

    private @Id @GeneratedValue Long id;
    private Long employeeId;
    private Date dateFrom;
    private Date dateTo;
    private String note;
    private String status;
    private Long updateBy;
    private Date updateTime;

    public Vacation() {
    }

    public Vacation(Long id, Long employeeId, Date dateFrom, Date dateTo, String note, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
        this.status = status;
    }

    public Vacation(Long id, String status, Long updateBy, Date updateTime) {
        this.id = id;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedBy() {
        return updateBy;
    }

    public void setUpdatedBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setVacation(Vacation vacation) {
        this.employeeId = vacation.getEmployeeId();
        this.dateFrom = vacation.getDateFrom();
        this.dateTo = vacation.getDateTo();
        this.note = vacation.getNote();
        this.status = vacation.status;
    }

    public void setApprove(String status, Long updateBy, Date updateTime){
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return status == vacation.status &&
                Objects.equals(id, vacation.id) &&
                Objects.equals(employeeId, vacation.employeeId) &&
                Objects.equals(dateFrom, vacation.dateFrom) &&
                Objects.equals(dateTo, vacation.dateTo) &&
                Objects.equals(note, vacation.note) &&
                Objects.equals(updateBy, vacation.updateBy) &&
                Objects.equals(updateTime, vacation.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, dateFrom, dateTo, note, status, updateBy, updateTime);
    }

    @Override
    public String toString() {
        return "Vacation{" + "id=" + this.id + ", employeeId='" + this.employeeId + '\'' + ", dateFrom='" + this.dateFrom
                + '\'' + ", dateTo='" + this.dateTo + '\'' + ", status='" + this.status + '\'' + ", updateBy='" + this.updateBy
                + '\'' + ", updateTime='" + this.updateTime + '\'' + ", note='" + this.note + '\'' + '}';
    }
}
