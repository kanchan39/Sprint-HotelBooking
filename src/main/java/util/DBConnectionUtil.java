package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/hotel_etl";

    private static final String USER = "root";
    private static final String PASSWORD = "Harsh092004";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}