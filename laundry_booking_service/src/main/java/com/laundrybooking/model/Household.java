package com.laundrybooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="household")
public class Household {

    @Id
    private Integer id;
    @Column(name="apartment_number")
    private Integer apartmentNumber;
    @Column(name="owner_name")
    private String ownerName;

    public Household() {
    }

    public Household(final Integer id, final Integer apartmentNumber, final String ownerName) {
        this.id = Objects.requireNonNull(id);
        this.apartmentNumber = Objects.requireNonNull(apartmentNumber);
        this.ownerName = Objects.requireNonNull(ownerName);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(final Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setHourSlot(final String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "{"
            + "'id': " + id
            + ", 'apartmentNumber': " + apartmentNumber
            + ", 'ownerName': " + "'" + ownerName + "'"
            + "}";
    }
}
