package com.company.serviceImpl;

import com.company.dto.BookingDTO;
import com.company.entity.Booking;
import com.company.entity.Coach;
import com.company.entity.UserTable;
import com.company.exception.BookingAlreadyExistException;
import com.company.repository.BookingRepository;
import com.company.repository.CoachRepository;
import com.company.repository.UserRepository;
import com.company.service.BookingService;
import com.company.utility.CompanyConstants;
import com.company.utility.MailUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@PropertySource("classpath:ValidationMessages.properties")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private Environment env;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoachRepository coachRepository;

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BookingDTO bookAppointment(String userid, String coachid, LocalDate appointmentDate, String slot) throws BookingAlreadyExistException {

        List<Booking> bookings = bookingRepository.findAllBookings(userid, appointmentDate, slot);

        if (bookings.size() > 0) {
            throw new BookingAlreadyExistException(env.getProperty(CompanyConstants.BOOKING_ALREADY_EXISTS.getValue()));
        }

        Booking booking = new Booking();
        booking.setAppointmentDate(appointmentDate);
        booking.setSlot(slot);
        booking.setCoachId(coachid);
        booking.setUserId(userid);
        Booking savedBooking = bookingRepository.save(booking);

        UserTable userTable = userRepository.findById(userid).get();
        Coach coach = coachRepository.findById(coachid).get();

        mailUtil.sendAppointmentMail(userTable.getName(), coach.getName(), userTable.getEmail(), savedBooking.getBookingId(), savedBooking.getSlot(), savedBooking.getAppointmentDate());

        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    @Override
    public BookingDTO rescheduleAppointment(Integer bookingid, LocalDate appointmentDate, String slot) throws BookingAlreadyExistException {

        Booking booking = bookingRepository.findById(bookingid).get();

        List<Booking> bookings = bookingRepository.findAllBookings(booking.getUserId(), appointmentDate, slot);

        if (bookings.size() > 0) {
            throw new BookingAlreadyExistException(env.getProperty(CompanyConstants.BOOKING_ALREADY_EXISTS.getValue()));
        }


        booking.setAppointmentDate(appointmentDate);
        booking.setSlot(slot);

        Booking savedBooking = bookingRepository.save(booking);

        UserTable userTable = userRepository.findById(savedBooking.getUserId()).get();
        Coach coach = coachRepository.findById(savedBooking.getCoachId()).get();

        mailUtil.sendReschedulingMail(userTable.getName(), coach.getName(), userTable.getEmail(), savedBooking.getBookingId(), savedBooking.getSlot(), savedBooking.getAppointmentDate());

        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    @Override
    public String cancelAppointment(Integer bookingid) {
        BookingDTO bookingDTO = findByBookingId(bookingid);
        UserTable userTable = userRepository.findById(bookingDTO.getUserId()).get();
        Coach coach = coachRepository.findById(bookingDTO.getCoachId()).get();

        bookingRepository.deleteById(bookingid);

        mailUtil.sendCancelAppointmentMail(userTable.getName(), coach.getName(), userTable.getEmail(), bookingDTO.getBookingId(), bookingDTO.getSlot(), bookingDTO.getAppointmentDate());

        return bookingid + " Deleted Successfully";
    }

    @Override
    public BookingDTO findByBookingId(Integer bookingId) {

        return modelMapper.map(bookingRepository.findById(bookingId).get(), BookingDTO.class);

    }

    @Override
    public List<BookingDTO> findBookingByUserId(String userid) {
        return bookingRepository.findBookingByUserId(userid).stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> findBookingByCoachId(String coachid) {
        return bookingRepository.findBookingByCoachId(coachid).stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }
}
