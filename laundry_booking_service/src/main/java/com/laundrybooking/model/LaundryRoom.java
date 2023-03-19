package com.laundrybooking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="laundry_room")
public class LaundryRoom {

    @Id
    private Integer id;
    private String name;

    public LaundryRoom() {
    }

    public LaundryRoom(final Integer id, final String name) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{"
            + "'id': " + id
            + ", 'name': " + "'" + name + "'"
            + "}";
    }
}
