package com.application.kppro_project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
//@Table(name = "feedback")
public class Feedback {

    private @Id @GeneratedValue Long id;
    private int quality;
    private String note;
    private Long author;
    private Date creationTime;
    private Long employeeId;

    public Feedback() {
    }

    public Feedback(Long id, int quality, String note, Long author, Date creationTime, Long employeeId) {
        this.id = id;
        this.quality = quality;
        this.note = note;
        this.author = author;
        this.creationTime = creationTime;
        this.employeeId = employeeId;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id &&
                quality == feedback.quality &&
                author == feedback.author &&
                employeeId == feedback.employeeId &&
                note.equals(feedback.note) &&
                creationTime.equals(feedback.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quality, note, author, creationTime, employeeId);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", quality=" + quality +
                ", note='" + note + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
