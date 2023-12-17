package com.company.controller;


import com.company.dto.BookingDTO;
import com.company.exception.BookingAlreadyExistException;
import com.company.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin("http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserid(@PathVariable("userid") String userid) {
        return ResponseEntity.ok(bookingService.findBookingByUserId(userid));
    }


    @GetMapping("/coach/{coachid}")
    public ResponseEntity<List<BookingDTO>> getBookingsByCoachid(@PathVariable("coachid") String coachid) {
        return ResponseEntity.ok(bookingService.findBookingByCoachId(coachid));
    }

    @PostMapping("/users/{userid}/booking/{coachid}")
    public ResponseEntity<BookingDTO> bookAppointment(@PathVariable("userid") String userid, @PathVariable("coachid") String coachid,
                                                      @RequestParam("slot") String slot, @RequestParam("dateofappointment") LocalDate dateofappointment) throws BookingAlreadyExistException {
        return new ResponseEntity<>(bookingService.bookAppointment(userid, coachid, dateofappointment, slot), HttpStatus.CREATED);
    }

    @PutMapping("/{bookingid}")
    public ResponseEntity<BookingDTO> rescheduleAppointment(@PathVariable("bookingid") Integer bookingid,
                                                            @RequestParam("slot") String slot, @RequestParam("dateofappointment") LocalDate dateofappointment) throws BookingAlreadyExistException {
        return new ResponseEntity<>(bookingService.rescheduleAppointment(bookingid, dateofappointment, slot), HttpStatus.OK);
    }

    @DeleteMapping("/{bookingid}")
    public ResponseEntity<String> cancelAppointment(@PathVariable("bookingid") Integer bookingid) {

        return new ResponseEntity<>(bookingService.cancelAppointment(bookingid), HttpStatus.OK);
    }


}
