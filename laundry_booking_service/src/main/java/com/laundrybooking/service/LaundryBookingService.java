package com.laundrybooking.service;

import com.laundrybooking.model.Booking;
import com.laundrybooking.model.Bookings;
import com.laundrybooking.model.LaundryRoom;
import com.laundrybooking.model.LaundryRooms;
import com.laundrybooking.model.request.BookingRequest;
import com.laundrybooking.repository.LaundryBookingRepository;
import com.laundrybooking.repository.LaundryHouseholdRepository;
import com.laundrybooking.repository.LaundryLaundryRoomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LaundryBookingService {
    private final LaundryBookingRepository laundryBookingRepository;
    private final LaundryLaundryRoomRepository laundryLaundryRoomRepository;
    private final LaundryHouseholdRepository laundryHouseholdRepository;

    public LaundryBookingService(final LaundryBookingRepository laundryBookingRepository, final LaundryLaundryRoomRepository laundryLaundryRoomRepository,
        final LaundryHouseholdRepository laundryHouseholdRepository) {
        this.laundryBookingRepository = Objects.requireNonNull(laundryBookingRepository);
        this.laundryLaundryRoomRepository = Objects.requireNonNull(laundryLaundryRoomRepository);
        this.laundryHouseholdRepository = Objects.requireNonNull(laundryHouseholdRepository);
    }

    public LaundryRooms getLaundryRooms() {
        return LaundryRooms.of(laundryLaundryRoomRepository.getLaundryRooms().stream()
            .toList());
    }

    public Bookings getBookings() {
        return Bookings.of(laundryBookingRepository.getBookings().stream()
                .sorted(Comparator.comparing(Booking::getDate)
                    .thenComparing(Booking::getHourSlot))
            .toList());
    }

    @Transactional
    public void createBooking(final BookingRequest request) {
        if (checkDateAndHourSlot(request)) {
            throw new BadRequestException("Date must be ahead of time and hour slot must be between 7-22");
        }

        final var requestedHouseholdId = Optional.ofNullable(laundryHouseholdRepository.getHouseholdById(request.getHouseholdId()));
        if (requestedHouseholdId.isEmpty()) {
            throw new BadRequestException("Not a valid household");
        }

        final var laundryRooms = laundryLaundryRoomRepository.getLaundryRooms().stream()
            .map(LaundryRoom::getId)
            .collect(Collectors.toSet());
        if (!laundryRooms.contains(request.getLaundryRoomId())) {
            throw new BadRequestException("Not a valid laundry room");
        }

        final var bookingForSelectedTimeSlot = Optional.ofNullable(laundryBookingRepository.getBookingForSelectedTimeSlot(request.getLaundryRoomId(), getDate(request.getDate()), request.getHourSlot()));
        if (bookingForSelectedTimeSlot.isPresent()) {
            throw new NotAllowedException("There is no booking available on this time");
        }
        laundryBookingRepository.createBooking(nextId(), request.getHouseholdId(), request.getLaundryRoomId(), request.getDate(), request.getHourSlot());
    }


    @Transactional
    public void deleteBooking(final Integer bookingId) {
        laundryBookingRepository.deleteBooking(bookingId);
    }

    private boolean checkDateAndHourSlot(final BookingRequest request) {
        return request.getDate().before(Date.valueOf(LocalDate.now())) ||
            hourOfDay(request) ||
            (request.getHourSlot() < 7 || request.getHourSlot() > 22);
    }

    private String getDate(final java.util.Date date) {
        final var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private boolean hourOfDay(final BookingRequest request) {
        final var date = ZonedDateTime.now(ZoneId.of("Europe/Stockholm"));
        final var localDate = request.getDate().toInstant()
            .atZone(ZoneId.of("Europe/Stockholm"))
            .toLocalDate();
        return localDate.isEqual(LocalDate.now()) && request.getHourSlot() < date.getHour();
    }

    private int nextId() {
        return Math.abs(new Random().nextInt());
    }

}
