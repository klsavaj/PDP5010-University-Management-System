package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection for the application.
 */
public class DatabaseConnection {

    // URL of the database.
    private static final String URL = "jdbc:mysql://localhost:3306/university_db"; 

    // Username for the database.
    private static final String USER = "root";

    // Password for the database.
    private static final String PASSWORD = "9328199824";

    /**
     * Establishes a connection to the database.
     *
     * @return a Connection object if the connection is successful.
     * @throws RuntimeException if the MySQL Driver is not found or if unable to connect to the database.
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established.");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL Driver not found!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database!");
        }
    }
}