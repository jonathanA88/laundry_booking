package com.laundrybooking.model;

import java.util.List;

public class LaundryRooms {

    private List<LaundryRoom> laundryRooms;

    private LaundryRooms() {
    }

    private LaundryRooms(final List<LaundryRoom> laundryRooms) {
        this.laundryRooms = List.copyOf(laundryRooms);
    }

    public static LaundryRooms of(final List<LaundryRoom> laundryRooms) {
        return new LaundryRooms(laundryRooms);
    }

    public List<LaundryRoom> getLaundryRooms() {
        return laundryRooms;
    }

    @Override
    public String toString() {
        return "{"
            + "'laundryRooms': " + laundryRooms
            + "}";
    }
}
