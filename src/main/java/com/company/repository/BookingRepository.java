package com.company.repository;

import com.company.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("select b from Booking b where b.userId = :userid and b.appointmentDate = :appointmentdate and b.slot=:slot")
    List<Booking> findAllBookings(@Param("userid") String userid, @Param("appointmentdate") LocalDate date, @Param("slot") String slot);

    List<Booking> findBookingByUserId(String userId);

    List<Booking> findBookingByCoachId(String coachId);
}
