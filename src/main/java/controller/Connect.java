package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
    private static final String URL =
            "jdbc:mysql://localhost:3306/projekat";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            return conn;

        } catch (SQLException e) {
            System.out.println("Greška pri konekciji.");
            e.printStackTrace();
            return null;
        }
    }
}
