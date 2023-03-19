package com.laundrybooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundrybooking.model.Booking;
import com.laundrybooking.model.Bookings;
import com.laundrybooking.model.request.BookingRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LaundryBookingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verifyGetAllBookings() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/bookings/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id", hasSize(0)));
    }

    @Test
    public void deleteBooking() throws Exception {
        // Check for zero bookings
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/bookings/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id", hasSize(0)));

        // Create one booking
        final var newBooking = new BookingRequest(1, 1, java.sql.Date.valueOf(LocalDate.now()), 20);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/bookings/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBooking))
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());

        // Check for 1 booking AND Get the id for the new booking
        final var result = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/bookings/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id", hasSize(1)))
            .andReturn();
        final var jsonResponse = result.getResponse().getContentAsString();
        final var bookings = fromJsonToObject(jsonResponse);
        final var newId = bookings.getBookings().stream()
            .findFirst()
            .map(Booking::getId)
            .orElse(null);

        // Delete booking
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/bookings/{id}/", newId) )
            .andExpect(status().isOk());

        // Check for zero bookings
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/bookings/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id", hasSize(0)));
    }

    @Test
    public void verifyPostNewBooking() throws Exception {
        // Check for zero bookings
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/bookings/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id", hasSize(0)));

        // Create new booking
        final var newBooking = new BookingRequest(1, 1, java.sql.Date.valueOf(LocalDate.now()), 20);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/bookings/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBooking))
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());

        // Check for one booking
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/bookings/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..id", hasSize(1)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Bookings fromJsonToObject(final String json) {
        try {
            return new ObjectMapper().readValue(json, Bookings.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
