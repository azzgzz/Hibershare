package ru.azzgzz.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseJDBC {

    private static void connect() {
        String url = "jdbc:postgresql://localhost/hibernate";
        String user = "hibernate";
        String password = "holopass";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection established!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
