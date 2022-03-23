package com.lset.bookingsystem.dao;

import com.lset.bookingsystem.bean.FlightBean;
import com.lset.bookingsystem.utility.DataBaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAOImpl implements PassengerDAO {
    //TODO IMPLEMENTATION

    private static Connection connection = null;

    @Override
    public int checkSeatAvailability(String flightNumber, String date) throws SQLException {
        connection = DataBaseUtility.createConnection();

        int totalCapacityResult = getTotalCapacityResult(flightNumber);
        int bookingsCount = getBookingsCount(flightNumber, date);
        return totalCapacityResult - bookingsCount;
    }

    private int getBookingsCount(String flightNumber, String date) throws SQLException {
        ResultSet resultSet = selectBasedOnFlightNoAndDate(flightNumber, date);
        int bookingsCount = 0;
        while (resultSet.next()) {
            bookingsCount++;
        }
        return bookingsCount;
    }


    private int getTotalCapacityResult(String flightNumber) throws SQLException {
        PreparedStatement totalCapacity = connection.prepareStatement("SELECT Capacity FROM Flight_Details WHERE Flight_No = ?");
        totalCapacity.setString(1, flightNumber);
        ResultSet resultSetTotalCapacity = totalCapacity.executeQuery();
        int totalCapacityResult = 0;
        while (resultSetTotalCapacity.next()) {
            totalCapacityResult = resultSetTotalCapacity.getInt("Capacity");
        }
        return totalCapacityResult;
    }


    // 2. Check Flight Details between Location and Destination

    @Override
    public List<FlightBean> checkFlightDetails(String departureLocation, String destinationLocation, String date) throws SQLException {
        FlightBean flightBean;
        List<FlightBean> flightList = new ArrayList<>();
        connection = DataBaseUtility.createConnection();

        ResultSet flightNumbersResults = selectBasedOnLocationsAndDate(departureLocation, destinationLocation, date);
        //List<String> flightNumbersList = new ArrayList<>();

        while (flightNumbersResults.next()) {
            String flightNumber = flightNumbersResults.getString("Flight_No");
            flightBean = new FlightBean();
            ResultSet resultSet = selectByFlightNumber(flightNumber);

            while (resultSet.next()) {

                flightBean.setFlightNumber(flightNumber);
                flightBean.setCompanyName(resultSet.getString("Company_Name"));
                flightBean.setDepartureTime(resultSet.getString("Departure_Time"));
                flightBean.setArrivalTime(resultSet.getString("Arrival_Time"));
                flightBean.setCost(resultSet.getDouble("Cost"));
                flightBean.setAvailableSeats(checkSeatAvailability(flightNumber, date));
                flightList.add(flightBean);
            }
        }
        return flightList;
    }


    private ResultSet selectBasedOnLocationsAndDate(String departureLocation, String destinationLocation, String date) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement flightNumbers = connection.prepareStatement("SELECT Flight_No FROM Booking_Details WHERE Departure_Location = ? AND Destination_Location = ? AND Date = ?");
        flightNumbers.setString(1, departureLocation);
        flightNumbers.setString(2, destinationLocation);
        flightNumbers.setString(3, date);
        ResultSet flightNumbersResults = flightNumbers.executeQuery();
        return flightNumbersResults;
    }

    private ResultSet selectByFlightNumber(String flightNumber) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement getFlightDetailsBasedOnId = connection.prepareStatement("SELECT * FROM Flight_Details WHERE Flight_No = ?");
        getFlightDetailsBasedOnId.setString(1, flightNumber);
        ResultSet resultSet = getFlightDetailsBasedOnId.executeQuery();
        return resultSet;
    }

    private ResultSet selectBasedOnFlightNoAndDate(String flightNumber, String date) throws SQLException {
        PreparedStatement bookings = connection.prepareStatement("SELECT * FROM Booking_Details WHERE Flight_No = ? AND Date = ?");
        bookings.setString(1, flightNumber);
        bookings.setString(2, date);
        ResultSet resultSet = bookings.executeQuery();
        return resultSet;
    }


    public boolean flightExists(String flightNumber) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectFlight = connection.prepareStatement("SELECT Flight_No FROM Flight_Details WHERE Flight_No = ?;");
        selectFlight.setString(1, flightNumber);
        ResultSet resultFromSelection = selectFlight.executeQuery();
        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }

    public boolean departureLocationExists(String departureLocation) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectDepartureLocation = connection.prepareStatement("SELECT Departure_Location FROM Flight_Details WHERE Departure_Location = ?;");
        selectDepartureLocation.setString(1, departureLocation);
        ResultSet resultFromSelection = selectDepartureLocation.executeQuery();
        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }

    public boolean destinationLocationExists(String destinationLocation) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectDestinationLocation = connection.prepareStatement("SELECT Destination_Location FROM Flight_Details WHERE Destination_Location = ?;");
        selectDestinationLocation.setString(1, destinationLocation);
        ResultSet resultFromSelection = selectDestinationLocation.executeQuery();
        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }
    public boolean bookingExists(int ticketNumber) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectBooking = connection.prepareStatement("SELECT Booking_Id FROM Booking_Details WHERE Booking_Id = ?;");
        selectBooking.setInt(1, ticketNumber);
        ResultSet resultFromSelection = selectBooking.executeQuery();
        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }
    public boolean dateExists(String date) throws SQLException {
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectBooking = connection.prepareStatement("SELECT Date FROM Booking_Details WHERE Date = ?;");
        selectBooking.setString(1, date);
        ResultSet resultFromSelection = selectBooking.executeQuery();
        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }

    public double getFlightCost(String flightNumber) throws SQLException {
        connection = DataBaseUtility.createConnection();
        double cost = 0;
        PreparedStatement selectCost = connection.prepareStatement("SELECT Cost FROM Flight_Details WHERE Flight_No = ?");
        selectCost.setString(1, flightNumber);
        ResultSet selectedCost = selectCost.executeQuery();
        while (selectedCost.next()) {
            cost = selectedCost.getDouble("Cost");
        }
        return cost;
    }

    public void bookTicket(int customerId, String flightNumber, String date) throws SQLException {
        connection = DataBaseUtility.createConnection();
        String departureLocation = null;
        String destination = null;
        String departure = null;
        String arrival = null;
        double cost = 0;
        ResultSet resultSet = selectByFlightNumber(flightNumber);
        while (resultSet.next()) {
            departureLocation = resultSet.getString("Departure_Location");
            destination = resultSet.getString("Destination_Location");
            departure = resultSet.getString("Departure_Time");
            arrival = resultSet.getString("Arrival_Time");
            cost = resultSet.getDouble("Cost");

        }

        insertBooking(customerId, flightNumber, date, departureLocation, destination, departure, arrival, cost);

    }

    private void insertBooking(int customerId, String flightNumber, String date, String departureLocation, String destination, String departure, String arrival, double cost) throws SQLException {
        PreparedStatement newTicket = connection.prepareStatement("INSERT INTO Booking_Details(Customer_Id, Flight_No, Departure_Location, Destination_Location, Date, Departure_Time, Arrival_Time, Cost) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        newTicket.setInt(1, customerId);
        newTicket.setString(2, flightNumber);
        newTicket.setString(3, departureLocation);
        newTicket.setString(4, destination);
        newTicket.setString(5, date);
        newTicket.setString(6, departure);
        newTicket.setString(7, arrival);
        newTicket.setDouble(8, cost);
        newTicket.executeUpdate();
    }

    public int getLastCreatedBookingId() throws SQLException {
        //TODO better selection is needed
        connection = DataBaseUtility.createConnection();
        PreparedStatement getPassengerId = connection.prepareStatement("SELECT MAX(Booking_Id) as Booking_Id from Booking_Details;");

        ResultSet result = getPassengerId.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt("Booking_Id");
        }
        return id;
    }

    //TODO customer exists based on customer id

    public boolean customerExists(int customerId) throws SQLException {
        //TODO make it return customer id in order for  getCustomerId to be scraped
        connection = DataBaseUtility.createConnection();
        PreparedStatement selectPassenger = connection.prepareStatement("SELECT Customer_Id FROM Customer_Details WHERE Customer_Id = ?");
        selectPassenger.setInt(1, customerId);
        ResultSet resultFromSelection = selectPassenger.executeQuery();

        if (resultFromSelection.next()) {
            return true;
        } else return false;
    }

    public void deleteBooking(int bookingId) throws Exception {
        connection = DataBaseUtility.createConnection();

        PreparedStatement deleteBooking = connection.prepareStatement("DELETE FROM Booking_Details WHERE Booking_Id = ?");
        deleteBooking.setInt(1, bookingId);
        deleteBooking.executeUpdate();
    }

}


  /* PreparedStatement flightFrequency = connection.prepareStatement("SELECT frequency FROM Flight_Details WHERE Flight_No = ?");
        flightFrequency.setString(1, flightNumber);
        flightFrequency.executeUpdate(); */