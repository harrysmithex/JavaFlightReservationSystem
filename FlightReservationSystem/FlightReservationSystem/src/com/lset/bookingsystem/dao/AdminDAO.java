package com.lset.bookingsystem.dao;

import com.lset.bookingsystem.bean.FlightBean;
import com.lset.bookingsystem.bean.PassengerBean;

import java.sql.SQLException;

public interface AdminDAO {

    boolean customerExists(String passportNumber) throws SQLException;

    int getCustomerId(String passportNumber) throws SQLException;

    boolean flightExists(String flightNumber) throws SQLException;

    int insertPassenger(PassengerBean passengerBean) throws Exception;

    String insertFlight(FlightBean flightBean) throws Exception;

    String insertFlightDetails(FlightBean flight) throws SQLException;

    void deletePassenger(int passengerId) throws Exception;

    void deleteFlight(String flightNo) throws Exception;

}
