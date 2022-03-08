package com.aff.baseDB;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.aff.utilities.ApplicationProperties;

public class DBConnections  {

    public static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static ResultSetMetaData resultSetMetaData = null;
    protected static ApplicationProperties dbProperties;
    
    public DBConnections() {
    	dbProperties = ApplicationProperties.getInstance("db");
    }

    public static void getConnection() throws FileNotFoundException, IOException {
        try {
        	
            Class.forName(dbProperties.getProperty("DB_DRIVER"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please check the path of DB2 driver: " + e.getMessage());
        }

        System.out.println("DB2 driver was loaded successfully!");

        try {

            connection = DriverManager.getConnection(dbProperties.getProperty("DB_HOST"), dbProperties.getProperty("DB_USER"), dbProperties.getProperty("DB_PASS"));
            if (connection != null) {
                System.out.println("Connected to the database \"" + connection.getSchema() + "\" successfully!");
            } else {
                throw new RuntimeException("Database connection failed. Please try again with correct details.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed. \nReason: " + e.getMessage());
        }

    }

    public static void executeQuery(String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
        } catch (Exception e) {
            throw new RuntimeException("Unable to execute the given query. \nReason: " + e.getMessage());
        }
    }
    public static String getValueWithColumnName(String query, String columnName) throws FileNotFoundException, IOException {

        getConnection();
        executeQuery(query);
        boolean records = false;
        String value = null;

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    records = true;
                    value = resultSet.getString(columnName);
                }
            }
            if (records == false) {
                throw new RuntimeException("No records found with the given query.");
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Unable to get the results. \nReason: " + e.getMessage());
        }

    }

}

