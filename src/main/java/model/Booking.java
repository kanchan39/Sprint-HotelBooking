package model;

import java.time.LocalDate;

public class Booking {

    public Integer bookingId;
    public Integer hotelId;
    public Integer customerId;

    public String fullName;
    public String email;
    public String phoneNumber;

    public LocalDate bookingDate;
    public LocalDate checkinDate;
    public LocalDate checkoutDate;

    public String roomType;
    public Double roomRate;
    public Integer numGuests;

    public String bookingChannel;
    public String bookingStatus;

    public String paymentStatus;
    public String paymentMethod;

    public Double taxAmount;
    public Double discountAmount;

    public String city;
    public String state;
    public String country;

    // Derived field
    public Long stayNights;
}