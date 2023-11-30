package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseHandler is responsible for establishing and managing a connection to the SQLite database.
 */
public class DatabaseConnection {

    /** JDBC URL for the SQLite database. */
    private static final String DB_URL = "jdbc:sqlite:database.db";

    /** The active connection to the SQLite database. */
    private static Connection connection;

    /**
     * Default constructor that initializes the database connection.
     */
    public void DatabaseHandler() {
        connect();
    }

    /**
     * Establishes a connection to the SQLite database.
     * If there's an issue during connection, it prints an error message.
     */
    private static void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Failed to establish a connection to the SQLite database: " + e.getMessage());
        }
    }

    /**
     * Retrieves the active database connection.
     *
     * @return Connection to the SQLite database.
     */
    public static Connection getConnection() {
        // Reconnect if connection was closed or is null
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.err.println("Error checking the database connection status: " + e.getMessage());
        }
        return connection;
    }

}