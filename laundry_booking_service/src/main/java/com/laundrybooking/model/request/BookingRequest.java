package com.laundrybooking.model.request;

import java.util.Date;
import java.util.Objects;

public class BookingRequest {

    private Integer laundryRoomId;
    private Integer householdId;
    private Date date;
    private Integer hourSlot;

    public BookingRequest() {
    }

    public BookingRequest(final Integer laundryRoomId, final Integer householdId, final Date date, final Integer hourSlot) {
        this.laundryRoomId = Objects.requireNonNull(laundryRoomId);
        this.householdId = Objects.requireNonNull(householdId);
        this.date = Objects.requireNonNull(date);
        this.hourSlot = Objects.requireNonNull(hourSlot);
    }

    public Integer getLaundryRoomId() {
        return laundryRoomId;
    }

    public void setLaundryRoomId(final Integer laundryRoomId) {
        this.laundryRoomId = laundryRoomId;
    }

    public Integer getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(final Integer householdId) {
        this.householdId = householdId;
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
            + "'laundryRoomId': " + laundryRoomId
            + ", 'householdId': " + householdId
            + ", 'date': " + date
            + ", 'hourSlot': " + hourSlot
            + "}";
    }
}
