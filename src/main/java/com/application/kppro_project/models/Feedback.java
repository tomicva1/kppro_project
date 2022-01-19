package com.application.kppro_project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
//@Table(name = "feedback")
public class Feedback {

    private @Id @GeneratedValue Long id;
    private int quality;
    private String note;
    private Long employeeId;

    public Feedback() {
    }

    public Feedback(Long id, int quality, String note, Long employeeId) {
        this.id = id;
        this.quality = quality;
        this.note = note;
        this.employeeId = employeeId;
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
        return quality == feedback.quality &&
                Objects.equals(id, feedback.id) &&
                Objects.equals(note, feedback.note) &&
                Objects.equals(employeeId, feedback.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quality, note, employeeId);
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
