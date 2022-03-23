package com.lset.bookingsystem.service;

import com.lset.bookingsystem.bean.FlightBean;
import com.lset.bookingsystem.bean.PassengerBean;
import com.lset.bookingsystem.dao.AdminDAO;
import com.lset.bookingsystem.dao.AdminDAOImpl;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {

    AdminDAO adminDAO = new AdminDAOImpl();


    @Override
    public boolean customerExists(String passportNumber) throws SQLException {
        boolean exists = false;
        try {
            exists = adminDAO.customerExists(passportNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public int insertPassenger(PassengerBean passengerBean) throws Exception {
        int id = 0;
        try {
            id = adminDAO.insertPassenger(passengerBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean flightExists(String flightNumber) throws SQLException{
        boolean exists = false;
        try {
            exists = adminDAO.flightExists(flightNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
    @Override
    public String insertFlight(FlightBean flightBean) throws Exception {
        String flightNumber = null;
        try {
            flightNumber = adminDAO.insertFlight(flightBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightNumber;
    }

    @Override
    public String insertFlightDetails(FlightBean flight) throws SQLException {
        String flightNumber = null;
        try {
            flightNumber = adminDAO.insertFlight(flight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightNumber;
    }

    @Override
    public void deletePassenger(int passengerId) throws Exception {
        try {
            adminDAO.deletePassenger(passengerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFlight(String flightNo) throws Exception {
        try {
            adminDAO.deleteFlight(flightNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
