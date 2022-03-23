package com.lset.bookingsystem.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseUtility {


    private static final String connectionString = "jdbc:mysql://localhost:3306/BookingSystem";

    private static final String username = "root";
    private static final String password = "simulatte";
    //private static final String dbName = "BookingSystem";
    private static Connection connection = null;

    public static Connection createConnection() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        return connection = DriverManager
                .getConnection(connectionString, properties);
    }

    public static void closeDBConnection() throws SQLException {
        connection.close();
    }
}
