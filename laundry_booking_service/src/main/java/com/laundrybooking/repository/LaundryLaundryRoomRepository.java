package com.laundrybooking.repository;

import com.laundrybooking.model.LaundryRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LaundryLaundryRoomRepository extends JpaRepository<LaundryRoom, Integer> {

    @Query(
        value = "SELECT * FROM laundry_room",
        nativeQuery = true)
    Collection<LaundryRoom> getLaundryRooms();
}
