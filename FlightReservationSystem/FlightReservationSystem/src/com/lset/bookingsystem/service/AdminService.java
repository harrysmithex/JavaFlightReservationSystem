package com.lset.bookingsystem.service;

import com.lset.bookingsystem.bean.FlightBean;
import com.lset.bookingsystem.bean.PassengerBean;

import java.sql.SQLException;

public interface AdminService  {

    public boolean customerExists(String passportNumber) throws SQLException;
    public boolean flightExists(String flightNumber) throws SQLException;
    public int insertPassenger(PassengerBean passengerBean) throws Exception;
    public String insertFlightDetails(FlightBean flight) throws SQLException;
    public String insertFlight(FlightBean flightBean) throws Exception;
    public void deletePassenger(int passengerId) throws Exception;
    public void deleteFlight(String flightNo) throws Exception;
}
