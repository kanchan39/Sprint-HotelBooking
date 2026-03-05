package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL      = "jdbc:mysql://localhost:3306/hotel_db"
                                         ;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Kanchan@039";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found! Check pom.xml", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing connection...");
        try (Connection conn = getConnection()) {
            System.out.println("SUCCESS! Connected to: " + conn.getCatalog());
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}