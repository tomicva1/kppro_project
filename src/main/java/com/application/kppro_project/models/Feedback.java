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

    private @Id @GeneratedValue long id;
    private int quality;
    private String note;
    private long author;
    private Date creationTime;
    private long employeeId;

    public Feedback() {
    }

    public Feedback(long id, int quality, String note, long author, Date creationTime, long employeeId) {
        this.id = id;
        this.quality = quality;
        this.note = note;
        this.author = author;
        this.creationTime = creationTime;
        this.employeeId = employeeId;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
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
