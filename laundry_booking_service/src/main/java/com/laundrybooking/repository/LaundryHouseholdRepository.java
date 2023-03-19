package com.laundrybooking.repository;

import com.laundrybooking.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LaundryHouseholdRepository extends JpaRepository<Household, Integer> {

    @Query(
        value = "SELECT * FROM household",
        nativeQuery = true)
    Collection<Household> getHouseholds();

    @Query(
        value = "SELECT * FROM household WHERE id = :id",
        nativeQuery = true)
    Household getHouseholdById(@Param("id") final Integer id);
}
