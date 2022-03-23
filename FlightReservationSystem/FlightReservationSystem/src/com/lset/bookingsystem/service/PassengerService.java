package com.lset.bookingsystem.service;

import com.lset.bookingsystem.bean.FlightBean;

import java.sql.SQLException;
import java.util.List;

public interface PassengerService {
    boolean flightExists(String flightNumber) throws SQLException;

    int checkSeatAvailability(String flightNumber, String date) throws SQLException;

    boolean departureLocationExists(String departureLocation) throws SQLException;

    boolean destinationLocationExists(String destinationLocation) throws SQLException;

    boolean bookingExists(int ticketNumber) throws SQLException;

     boolean dateExists(String date) throws SQLException;

    List<FlightBean> checkFlightDetails(String departureLocation, String destinationLocation, String date) throws SQLException;

    double getFlightCost(String flightNumber) throws SQLException;

    void bookTicket(int customerId, String flightNumber, String date) throws SQLException;

    int getLastCreatedBookingId() throws SQLException;

    boolean customerExists(int customerId) throws SQLException;

    void deleteBooking(int bookingId) throws Exception;


}
