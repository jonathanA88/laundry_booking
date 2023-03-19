package com.laundrybooking.repository;

import com.laundrybooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface LaundryBookingRepository extends JpaRepository<Booking, Integer> {

    @Query(
        value = "SELECT * FROM booking",
        nativeQuery = true)
    Collection<Booking> getBookings();

    @Modifying
    @Query(
        value =
            "insert into booking (id, household_id, laundry_room_id, date, hour_slot) values (:id, :household_id, :laundry_room_id, :date, :hour_slot)",
        nativeQuery = true)
    void createBooking(@Param("id") Integer id, @Param("household_id") Integer householdId,
        @Param("laundry_room_id") Integer laundryRoomId, @Param("date") Date date, @Param("hour_slot") Integer hourSlot);

    @Modifying
    @Query(
        value =
            "delete from booking b where b.id = :id",
        nativeQuery = true)
    void deleteBooking(@Param("id") Integer bookingId);

    @Query(
        value =
            "select * from booking b where b.laundry_room_id = :laundry_room_id and b.date = :date and b.hour_slot = :hour_slot",
        nativeQuery = true)
    Booking getBookingForSelectedTimeSlot(@Param("laundry_room_id") final Integer laundryRoomId,
        @Param("date") final String date, @Param("hour_slot") final Integer hourSlot);
}
