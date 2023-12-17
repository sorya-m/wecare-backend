package com.company.service;

import com.company.dto.BookingDTO;
import com.company.exception.BookingAlreadyExistException;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    public List<BookingDTO> getAllBookings();

    public BookingDTO bookAppointment(String userid, String coachid, LocalDate appointmentDate, String slot) throws BookingAlreadyExistException;

    public BookingDTO rescheduleAppointment(Integer bookingid, LocalDate appointmentDate, String slot) throws BookingAlreadyExistException;

    public String cancelAppointment(Integer bookingid);

    public BookingDTO findByBookingId(Integer bookingId);

    public List<BookingDTO> findBookingByUserId(String userid);

    public List<BookingDTO> findBookingByCoachId(String coachid);


}
