package com.application.kppro_project.models;

import com.application.kppro_project.enums.StatusEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
//@Table(name = "vacation")
public class Vacation {

    private @Id @GeneratedValue long id;
    private long employeeId;
    private Date dateFrom;
    private Date dateTo;
    private String note;
    private StatusEnum status;
    private long updateBy;
    private Date updateTime;

    public Vacation() {
    }

    public Vacation(long id, long employeeId, Date dateFrom, Date dateTo, String note, StatusEnum status) {
        this.id = id;
        this.employeeId = employeeId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
        this.status = status;
    }

    public Vacation(long id, StatusEnum status, long updateBy, Date updateTime) {
        this.id = id;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) { this.id = id; }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public long getUpdatedBy() {
        return updateBy;
    }

    public void setUpdatedBy(long updateBy) {
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

    public void setApprove(StatusEnum status, long updateBy, Date updateTime){
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
