package com.laundrybooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="booking")
public class Booking {

    @Id
    private Integer id;
    @Column(name="household_id")
    private Integer householdId;
    @Column(name="laundry_room_id")
    private Integer laundryRoomId;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name="hour_slot")
    private Integer hourSlot;

    public Booking() {
    }

    public Booking(final Integer id, final Integer householdId, final Integer laundryRoomId, final Date date, final Integer hourSlot) {
        this.id = Objects.requireNonNull(id);
        this.householdId = Objects.requireNonNull(householdId);
        this.laundryRoomId = Objects.requireNonNull(laundryRoomId);
        this.date = Objects.requireNonNull(date);
        this.hourSlot = Objects.requireNonNull(hourSlot);
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(final Integer householdId) {
        this.householdId = householdId;
    }

    public Integer getLaundryRoomId() {
        return laundryRoomId;
    }

    public void setLaundryRoomId(final Integer laundryRoomId) {
        this.laundryRoomId = laundryRoomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }
    public Integer getHourSlot() {
        return hourSlot;
    }

    public void setHourSlot(final Integer hourSlot) {
        this.hourSlot = hourSlot;
    }

    @Override
    public String toString() {
        return "{"
            + "'id': " + id
            + ", 'householdId': " + householdId
            + ", 'laundryRoomId': " + laundryRoomId
            + ", 'date': " + date
            + ", 'hourSlot': " + hourSlot
            + "}";
    }
}
