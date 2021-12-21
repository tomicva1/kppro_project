package com.application.kppro_project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "feedback")
public class Feedback {

    private @Id @GeneratedValue Long id;
    private int quality;
    private String note;
    private Long employee_id;

    public Feedback() {
    }

    public Feedback(Long id, int quality, String note, Long employee_id) {
        this.id = id;
        this.quality = quality;
        this.note = note;
        this.employee_id = employee_id;
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

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return quality == feedback.quality &&
                Objects.equals(id, feedback.id) &&
                Objects.equals(note, feedback.note) &&
                Objects.equals(employee_id, feedback.employee_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quality, note, employee_id);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", quality=" + quality +
                ", note='" + note + '\'' +
                ", employee_id=" + employee_id +
                '}';
    }
}
