package com.lset.bookingsystem.service;

import com.lset.bookingsystem.bean.FlightBean;
import com.lset.bookingsystem.dao.PassengerDAO;
import com.lset.bookingsystem.dao.PassengerDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class PassengerServiceImpl implements PassengerService {

    PassengerDAO passengerDAO = new PassengerDAOImpl();

    @Override
    public int checkSeatAvailability(String flightNumber, String date) throws SQLException {
        int availableSeats = 0;
        try {
           availableSeats = passengerDAO.checkSeatAvailability(flightNumber, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableSeats;
    }

    @Override
    public boolean departureLocationExists(String departureLocation) throws SQLException {
        boolean exists = false;
        try {
            exists = passengerDAO.departureLocationExists(departureLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean destinationLocationExists(String destinationLocation) throws SQLException {
        boolean exists = false;
        try {
            exists = passengerDAO.destinationLocationExists(destinationLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean bookingExists(int ticketNumber) throws SQLException {
        boolean exists = false;
        try {
            exists = passengerDAO.bookingExists(ticketNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean dateExists(String date) throws SQLException {
        boolean exists = false;
        try {
            exists = passengerDAO.dateExists(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public List<FlightBean> checkFlightDetails(String departureLocation, String destinationLocation, String date) throws SQLException {
        List<FlightBean> flightList = null;
        try {
            flightList = passengerDAO.checkFlightDetails(departureLocation, destinationLocation, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightList;
    }

    @Override
    public double getFlightCost(String flightNumber) throws SQLException {
        double cost = 0;
        boolean exists = false;
        try {
            cost = passengerDAO.getFlightCost(flightNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cost;
    }

    @Override
    public void bookTicket(int customerId, String flightNumber, String date) throws SQLException {
        try {
            passengerDAO.bookTicket(customerId, flightNumber, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLastCreatedBookingId() throws SQLException {
        int id = 0;
        try {
            id = passengerDAO.getLastCreatedBookingId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean customerExists(int customerId) throws SQLException {
        boolean exists = false;
        try {
            exists = passengerDAO.customerExists(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public void deleteBooking(int bookingId) throws Exception {
        try{
            passengerDAO.deleteBooking(bookingId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean flightExists(String flightNumber) throws SQLException{
        boolean exists = false;
        try {
            exists = passengerDAO.flightExists(flightNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
}


