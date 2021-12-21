package com.application.kppro_project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {

    private @Id @GeneratedValue Long id;
    private int map;
    private String description;
    private String name;

    public Department() {
    }

    public Department(Long id, int map, String description, String name) {
        this.id = id;
        this.map = map;
        this.description = description;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return map == that.map &&
                Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, map, description, name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", map=" + map +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
