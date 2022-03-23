package com.lset.bookingsystem.userinterface;

import com.lset.bookingsystem.bean.*;
import com.lset.bookingsystem.dao.*;
import com.lset.bookingsystem.service.*;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class User {

    static AdminService adminService;
    static PassengerService passengerService;

    public static void main(String[] args) throws Exception {

        adminService = new AdminServiceImpl();
        passengerService = new PassengerServiceImpl();
        menu();

        //TODO 1. all dates are currently Strings, need proper formatting in both table and implementation
        //TODO 2. flight capacity needs restriction (fixed size)
    }

    private static void menu() throws Exception {
        int mainManuInput = mainMenuChoice();
        switch (mainManuInput) {
            case 1 -> {
                adminDetails();
            }
            case 2 -> {
                passengerDetails();
            }
            case 3 -> {
                exit(0);
            }
        }
    }

    public static int mainMenuChoice() {
        System.out.printf("Menu: %n1. Admin%n2. Passenger%n3. Exit%n");
        System.out.printf("Select your option:%n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int adminMenuChoice() {
        System.out.printf("Admin Menu: %n1. Create Passenger%n2. Delete Passenger%n3. Create Flight Details%n4. Delete Flight Details%n5. Exit%n");
        System.out.printf("Select your option:%n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

        //another switch choice

    }

    public static int passengerMenuChoice() {
        System.out.printf("Passenger Menu: %n1. Check Seat Availability%n2. Check flight details between current location and destination%n3. Book Tickets%n4. Cancel Tickets%n5. Exit");
        System.out.printf("Select your option:%n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void adminDetails() throws Exception {
        Scanner sc = new Scanner(System.in);

        switch (adminMenuChoice()) {
            case 1:
                //TODO currently passenger should be allowed to book more than once for the same flight
                // but the table needs a field with a name of who is flying
                System.out.println("Enter passport number:%n");
                String passportNumber = sc.nextLine();


                while (adminService.customerExists(passportNumber)) {
                    System.out.printf("Customer already exists! Please try again!%n");
                    passportNumber = sc.nextLine();
                }
                PassengerBean passenger = passengerInfoInput(sc);
                int passengerId = adminService.insertPassenger(passenger);
                System.out.printf("Passenger with id %d created successfully!%n", passengerId);

                menu();

            case 2:
                System.out.printf("Enter passport number:%n");
                String psNumber = sc.nextLine();

                while (!adminService.customerExists(psNumber)) {
                    System.out.printf("Passenger doesn't exist! Please try again!%n");
                    psNumber = sc.nextLine();
                }
                AdminDAO adminDAO = new AdminDAOImpl();
                int customerId = adminDAO.getCustomerId(psNumber);
                adminService.deletePassenger(customerId);
                System.out.printf("Passenger with id %d deleted successfully!%n", customerId);

                menu();
            case 3:
                System.out.println("Enter flight number:%n");
                String flightNumber = sc.nextLine();

                while (!adminService.flightExists(flightNumber)) {
                    System.out.printf("Flight already exists! Please try again!%n");
                    flightNumber = sc.nextLine();
                }
                FlightBean flight = flightInfoInput(sc);
                String flightId = adminService.insertFlight(flight);
                System.out.printf("Flight with id %s created successfully!%n", flightId);
                menu();
            case 4:

                System.out.printf("Enter flight number:%n");
                String flightNo = sc.nextLine();

                while (!adminService.flightExists(flightNo)) {
                    System.out.println("Flight does not exist! Please try again!%n");
                    flightNo = sc.nextLine();
                }
                adminService.deleteFlight(flightNo);
                System.out.printf("Flight with number %s was deleted!%n", flightNo);
                menu();
            case 5:
                System.out.println("Thank you for using our platform!");
                exit(0);
        }
    }


    public static void passengerDetails() throws Exception {
        Scanner sc = new Scanner(System.in);

        switch (passengerMenuChoice()) {

            case 1:

                System.out.printf("Enter flight number:%n");
                String flightNumber = sc.nextLine();

                while (!passengerService.flightExists(flightNumber)) {
                    System.out.printf("Flight does not exist! Please try again!%n");
                    flightNumber = sc.nextLine();
                }
                //TODO check if flight date exists
                System.out.printf("Enter flight date:%n");
                String flightDate = sc.nextLine();
                while (!passengerService.dateExists(flightDate)) {
                    System.out.printf("There is no flight on that date! Please Try again!%n");
                    flightDate = sc.nextLine();
                }

                int availableSeats = passengerService.checkSeatAvailability(flightNumber, flightDate);
                System.out.printf("Number of seats available: %d", availableSeats);

                menu();
            case 2:
                System.out.printf("Enter departure location:%n");
                String departureLocation = sc.nextLine();

                while (!passengerService.departureLocationExists(departureLocation)) {
                    System.out.printf("Destination location does not exist! Please try again!%n");
                    departureLocation = sc.nextLine();
                    menu();
                }
                System.out.printf("Enter destination location:%n");
                String destinationLocation = sc.nextLine();
                while (!passengerService.destinationLocationExists(destinationLocation)) {
                    System.out.printf("Departure location does not exist! Please try again%n");
                    destinationLocation = sc.nextLine();
                    menu();
                }

                //TODO check if flight date exists
                System.out.printf("Enter flight date:%n");
                String date = sc.nextLine();
                while (!passengerService.dateExists(date)) {
                    System.out.printf("There is no flight on that date! Please Try again!%n");
                    date = sc.nextLine();
                }

                printTableNames();

                List<FlightBean> resultList = passengerService.checkFlightDetails(departureLocation, destinationLocation, date);
                resultList.forEach(flight -> {
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n",
                            flight.getFlightNumber(), flight.getCompanyName(),
                            flight.getDepartureTime(), flight.getArrivalTime(),
                            flight.getCost(), flight.getAvailableSeats());
                });

                menu();

            case 3:

                System.out.printf("Enter customer id:%n");
                int customerId = Integer.parseInt(sc.nextLine());

                while (!passengerService.customerExists(customerId)) {
                    System.out.printf("Customer id does not exist, please try again:%n");
                    customerId = sc.nextInt();
                }


                System.out.printf("Enter flight number:%n");
                String flightNo = sc.nextLine();

                double costToPay = 0;


                while (!passengerService.flightExists(flightNo)) {
                    System.out.printf("Flight does not exist, please try again:%n");
                    flightNo = sc.nextLine();
                }
                //TODO a check if flight date exists
                System.out.printf("Enter flight date:%n");
                String dateOfTheFlight = sc.nextLine();
                while (!passengerService.dateExists(dateOfTheFlight)) {
                    System.out.printf("There is no flight on that date! Please Try again!%n");
                    dateOfTheFlight = sc.nextLine();
                }

                costToPay = passengerService.getFlightCost(flightNo);

                System.out.printf("Pay the amount: %.2f%n", costToPay);
                double amountPaid = Double.parseDouble(sc.nextLine());
                if (amountPaid == costToPay) {
                    passengerService.bookTicket(customerId, flightNo, dateOfTheFlight);
                }
                int lastCreatedBookingId = passengerService.getLastCreatedBookingId();
                System.out.printf("Ticket number: %d%n", lastCreatedBookingId);
                System.out.printf("Thank you for using Flight Ticket Booking System!%n");
                menu();
            case 4:
                System.out.printf("Enter ticket number to be cancelled:%n");
                int ticketNumber = Integer.parseInt(sc.nextLine());
                double refund = 0;
                if (!passengerService.bookingExists(ticketNumber)) {
                    System.out.printf("This ticket number does not exist! Please try again:%n");
                    ticketNumber = Integer.parseInt(sc.nextLine());
                } else {
                    passengerService.deleteBooking(ticketNumber);
                    System.out.printf("Ticket has been canceled successfully%n");
                    System.out.println("For refund please enter flight number:%n");
                    String flNumber = sc.nextLine();
                    refund = passengerService.getFlightCost(flNumber);

                    System.out.printf("Refund amount: %.2f", refund);
                }
                menu();
            case 5:
                exit(0);
        }

    }

    private static FlightBean flightInfoInput(Scanner sc) {
        System.out.printf("Enter flight number:%n");
        String flightNumber = sc.nextLine();
        System.out.printf("Enter company name:%n");
        String companyName = sc.nextLine();
        System.out.printf("Enter airplane model:%n");
        String model = sc.nextLine();
        System.out.printf("Enter departure location:%n");
        String departureLocation = sc.nextLine();
        System.out.printf("Enter destination location:%n");
        String destinationLocation = sc.nextLine();

        System.out.printf("Enter flight frequency:%n");
        String frequency = sc.nextLine();
        System.out.printf("Enter flight's departure time:%n");
        String departureTime = sc.nextLine();
        System.out.printf("Enter flight's arrival time:%n");
        String arrivalTime = sc.nextLine();
        //TODO Flight capacity range needs to be specified
        System.out.printf("Enter flight capacity:%n");
        int capacity = sc.nextInt();
        System.out.printf("Enter flight cost:%n");
        double cost = sc.nextDouble();

        return new FlightBean(flightNumber,
                companyName, model, departureLocation,
                destinationLocation, frequency,
                departureTime, arrivalTime, capacity, cost);
    }

    private static PassengerBean passengerInfoInput(Scanner sc) {
        System.out.printf("In order to create a passenger, please enter the following details:%n");
        System.out.printf("Enter passenger's name:%n");
        String name = sc.nextLine();
        System.out.printf("Enter passenger's address:%n");
        String address = sc.nextLine();
        System.out.printf("Enter passenger's date of birth:%n");
        String dateOfBirth = sc.nextLine();
        System.out.printf("Enter passenger's passportNumber:%n");
        String passportNumber = sc.nextLine();

        return new PassengerBean(name, address, dateOfBirth, passportNumber);

    }

    private static void printTableNames() {
        String flightNo = "Flight No";
        String companyName = "Company";
        String departureTime = "Departure";
        String arrivalTime = "Arrival";
        String cost = "Cost";
        String availableSeats = "Seats Available";

        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", flightNo, companyName, departureTime, arrivalTime, cost, availableSeats);
    }


}

