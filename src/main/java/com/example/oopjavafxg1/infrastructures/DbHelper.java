package com.example.oopjavafxg1.infrastructures;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private static final String CONNECTION_URL =
            "jdbc:sqlserver://127.0.0.1:1433;encrypt=false;databaseName=Regjistrimi;username=sa;password=sa;";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(CONNECTION_URL);
        }
        return connection;
    }
}









