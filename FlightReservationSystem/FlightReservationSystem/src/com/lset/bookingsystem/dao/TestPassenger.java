package com.lset.bookingsystem.dao;

import com.lset.bookingsystem.bean.FlightBean;
import com.mysql.cj.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

public class TestPassenger {
    public static void main(String[] args) throws SQLException {

      PassengerDAOImpl pdi = new PassengerDAOImpl();
//        List<FlightBean> list = pdi.checkFlightDetails("London", "Mexico", "23:03:22");
//        for (FlightBean flightBean : list) {
//            System.out.println(flightBean.getFlightNumber());
//            System.out.println();
//            System.out.println(flightBean.getCompanyName());
//            System.out.println();
//            System.out.println(flightBean.getDepartureTime());
//            System.out.println();
//            System.out.println(flightBean.getCost());
//            System.out.println();
//            System.out.println(flightBean.getAvailableSeats());
//        }
//
//        System.out.println("...........................");
//        int i = pdi.checkSeatAvailability("1001", "23.03.22");

        int lastCreatedBookingId = pdi.getLastCreatedBookingId();
        System.out.println(lastCreatedBookingId);

//        String flightNo = "Flight No";
//        String companyName = "Company_Name";
//        String departureTime = "Departure Time";
//        String arrivalTime = "Arrival Time";
//        String cost = "Cost";
//        String availableSeats = "Seats Available";
//
//        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%n", flightNo, companyName, departureTime, arrivalTime, cost, availableSeats);

    }
}
