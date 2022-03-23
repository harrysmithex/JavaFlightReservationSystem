package com.lset.bookingsystem.bean;

public class FlightBean {
    //TODO Flight No - flight number can include letters
    private String flightNumber;
    //TODO FlightName is renamed to companyName
    private String companyName;
    private String model;
    private String departureLocation;
    private String destinationLocation;
    private String frequency;
    private String departureTime;
    private String arrivalTime;
    private int capacity;
    private double cost;
    private int availableSeats;

    public FlightBean(String flightNumber,
                      String companyName,
                      String model,
                      String departureLocation,
                      String destinationLocation,
                      String frequency,
                      String departureTime,
                      String arrivalTime,
                      int capacity,
                      double cost) {
        this.flightNumber = flightNumber;
        this.companyName = companyName;
        this.model = model;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.frequency = frequency;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.cost = cost;
    }

    public FlightBean() {}

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
