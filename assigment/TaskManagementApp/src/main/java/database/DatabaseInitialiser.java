package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitialiser {

    public static void initialiseDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Tasks (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT NOT NULL, " +
                "Description TEXT, " +
                "Status TEXT NOT NULL, " +
                "DueDate TEXT, " +  // Stored in ISO8601 format (YYYY-MM-DD)
                "Priority TEXT NOT NULL CHECK (Priority IN ('Low', 'Normal', 'High')), " +
                "CreationDate TEXT NOT NULL" +  // Stored in ISO8601 format (YYYY-MM-DD)
                ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Create the Task table
            stmt.execute(createTableSQL);
            System.out.println("Task table created successfully.");
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }
}
