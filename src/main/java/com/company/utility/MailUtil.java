package com.company.utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class MailUtil {


    @Autowired
    private JavaMailSender javaMailSender;

    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(10);

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void sendAppointmentMail(String userName, String coachName, String email, int bookingid, String bookingSlot,
                                    LocalDate appointmentDate) {
        log.info("Inside sendMail() method of {}", this.getClass());
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email);
                    msg.setSubject("Your apppoinment with coach " + coachName + " with booking id" + bookingid + " has been successfully scheduled.");
                    msg.setText("Dear" + userName + "your appointment with " + coachName +
                            " has been scheduled successfully.\n you can visit your coach any time from " +
                            bookingSlot + "on" + appointmentDate + "\n\n\nThanks and Regards\n Team WeCare");
                    javaMailSender.send(msg);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        quickService.submit(runnable);
        log.info("Booking: " + bookingid + " has been sent");

    }

    public void sendReschedulingMail(String userName, String coachName, String email, int bookingid, String bookingSlot,
                                     LocalDate appointmentDate) {
        log.info("Inside sendMail() method2 of {}", this.getClass());
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email);
                    msg.setSubject("Your apppoinment with coach " + coachName + " with booking id " + bookingid + " has been successfully rescheduled.");
                    msg.setText("Dear" + userName + "your appointment with " + coachName +
                            " has been rescheduled successfully.\n you can visit your coach any time from " +
                            bookingSlot + "on" + appointmentDate + "\n\n\nThanks and Regards\n Team WeCare");
                    javaMailSender.send(msg);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        quickService.submit(runnable);
        log.info("Booking: " + bookingid + " has been sent");

    }

    public void sendCancelAppointmentMail(String userName, String coachName, String email, int bookingid, String bookingSlot,
                                          LocalDate appointmentDate) {
        log.info("Inside sendMail() method2 of {}", this.getClass());
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email);
                    msg.setSubject("Your apppoinment with" + bookingid + "has been successfully Cancelled.");
                    msg.setText("Dear" + userName + "your appointment with " + coachName +
                            " has been cancelled successfully.\n you can visit your coach any time from " +
                            bookingSlot + "on" + appointmentDate + "\n\n\nThanks and Regards\n Team WeCare");
                    javaMailSender.send(msg);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        quickService.submit(runnable);
        log.info("Booking: " + bookingid + " has been sent");
    }


}

