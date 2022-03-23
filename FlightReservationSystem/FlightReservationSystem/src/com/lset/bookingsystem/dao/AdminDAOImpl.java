package com.lset.bookingsystem.dao;

import com.lset.bookingsystem.bean.FlightBean;
import com.lset.bookingsystem.bean.PassengerBean;
import com.lset.bookingsystem.utility.DataBaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {

    //TODO  DATA ACCESS OBJECT CLASS

    private static Connection connection = null;


    public int insertPassenger(PassengerBean passenger) throws Exception {
        connection = DataBaseUtility.createConnection();
        PreparedStatement insertPassenger = connection.prepareStatement("INSERT INTO Customer_Details VALUES(?, ?, ?, ?)");
        insertPassenger.setString(1, passenger.getName());
        insertPassenger.setString(2, passenger.getAddress());
        insertPassenger.setString(3, passenger.getDateOfBirth());
        insertPassenger.setString(4, passenger.getPassportNumber());
        insertPassenger.executeUpdate();
        return getLastCreatedCustomerId();

    }

    public boolean customerExists(String passportNumber) throws SQLException {
        //TODO make it return customer id in order for  getCustomerId to be scraped
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectPassenger = connection.prepareStatement("SELECT Passport_No FROM Customer_Details WHERE Passport_No = ?");
        selectPassenger.setString(1, passportNumber);
        ResultSet resultFromSelection = selectPassenger.executeQuery();

        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }

    public int getCustomerId(String passportNumber) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectPassenger = connection.prepareStatement("SELECT Customer_Id FROM Customer_Details WHERE Passport_No = ?");
        selectPassenger.setString(1, passportNumber);
        ResultSet resultFromSelection = selectPassenger.executeQuery();
        if (resultFromSelection.next()) {
            return resultFromSelection.getInt("Customer_Id");
        } else {
            return 0;
        }
    }

    private int getLastCreatedCustomerId() throws SQLException {
        //TODO better selection is needed
        connection = DataBaseUtility.createConnection();
        PreparedStatement getPassengerId = connection.prepareStatement("SELECT MAX(Customer_Id) as Customer_Id from Customer_Details;");

        ResultSet result = getPassengerId.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt("Customer_Id");
        }
        return id;
    }

    //TODO
    public boolean flightExists(String flightNumber) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectFlight = connection.prepareStatement("SELECT Flight_No FROM Flight_Details WHERE Flight_No = ?;");
        selectFlight.setString(1, flightNumber);
        ResultSet resultFromSelection = selectFlight.executeQuery();
        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }

    private String getFlightId() throws SQLException {
        PreparedStatement getFlightId = connection.prepareStatement("SELECT MAX(Flight_No) as Flight_No from Flight_Details;");
        ResultSet result = getFlightId.executeQuery();
        String flightNumber = null;
        while (result.next()) {
            flightNumber = result.getString("Flight_No");
        }
        return flightNumber;
    }


    public String insertFlight(FlightBean flight) throws Exception {
        return insertFlightDetails(flight);
    }

    public String insertFlightDetails(FlightBean flight) throws SQLException {
        connection = DataBaseUtility.createConnection();

        PreparedStatement insertFlight = connection.prepareStatement("INSERT INTO Flight_Details VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        insertFlight.setString(1, flight.getFlightNumber());
        insertFlight.setString(2, flight.getCompanyName());
        insertFlight.setString(3, flight.getModel());
        insertFlight.setString(4, flight.getDepartureLocation());
        insertFlight.setString(5, flight.getDestinationLocation());
        insertFlight.setString(6, flight.getFrequency());
        insertFlight.setString(7, flight.getDepartureTime());
        insertFlight.setString(8, flight.getArrivalTime());
        insertFlight.setInt(9, flight.getCapacity());
        insertFlight.setDouble(10, flight.getCost());
        insertFlight.executeUpdate();
        return getFlightId();
    }

    //TODO delete passenger from the data base
    public void deletePassenger(int passengerId) throws Exception {
        connection = DataBaseUtility.createConnection();

        PreparedStatement deletePassenger = connection.prepareStatement("DELETE FROM Customer_Details WHERE Customer_Id = ?");
        deletePassenger.setInt(1, passengerId);
        deletePassenger.executeUpdate();
    }


    //TODO delete flight from the data base
    public void deleteFlight(String flightNumber) throws Exception {
        connection = DataBaseUtility.createConnection();

        PreparedStatement deleteFlight = connection.prepareStatement("DELETE FROM Flight_Details WHERE Flight_No = ?");
        deleteFlight.setString(1, flightNumber);
        deleteFlight.executeUpdate();
    }

}

