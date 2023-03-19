package com.laundrybooking.model;

import java.util.List;

public class Bookings {

    private List<Booking> bookings;

    private Bookings() {
    }

    private Bookings(final List<Booking> bookings) {
        this.bookings = List.copyOf(bookings);
    }

    public static Bookings of(final List<Booking> bookings) {
        return new Bookings(bookings);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public String toString() {
        return "{"
            + "'bookings': " + bookings
            + "}";
    }
}
