package com.laundrybooking;

import com.laundrybooking.model.Bookings;
import com.laundrybooking.model.LaundryRooms;
import com.laundrybooking.model.request.BookingRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class LaundryBookingController {

    private final LaundryBookingService laundryBookingService;

    public LaundryBookingController(final LaundryBookingService laundryBookingService) {
        this.laundryBookingService = Objects.requireNonNull(laundryBookingService);
    }

    @GetMapping("/bookings/laundry-room/")
    public LaundryRooms getLaundryRooms() {
        return laundryBookingService.getLaundryRooms();
    }

    @GetMapping("/bookings/")
    public Bookings getBookings() {
        return laundryBookingService.getBookings();
    }

    @PostMapping("/bookings/")
    public void createBooking(@RequestBody final BookingRequest request) {
        laundryBookingService.createBooking(request);
    }

    @DeleteMapping("/bookings/{id}/")
    public void deleteBooking(@PathVariable("id") final Integer bookingId) {
        laundryBookingService.deleteBooking(bookingId);
    }
}
