package database;

import model.Movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Class for populating the database with movies from a JSON file.
 */
public class MovieDataPopulator {
    public static void main(String[] args) {
        // Create a custom ObjectMapper with JavaTimeModule for LocalDate support
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            // Load movies from the JSON file
            List<Movie> movies = objectMapper.readValue(new File("src/main/resources/json/dummy_movies.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Movie.class));


            createMoviesTableIfNotExists(); // Ensure connection is open

            // Add each movie to the database using the static method
            for (Movie movie : movies) {
                MovieDAO.addMovie(movie); // Pass the connection
            }

            System.out.println("Movies added to the database successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error: Failed to populate the database with movies.");
        }
    }

    /**
     * Create the 'movies' table if it does not exist.
     *
     * @throws SQLException If a database error occurs.
     */
    private static void createMoviesTableIfNotExists() throws SQLException {
        dropMoviesTableIfExists();
        String createTableQuery = "CREATE TABLE IF NOT EXISTS movies (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "director TEXT," +
                "year INTEGER," +
                "genre TEXT," +
                "rating INTEGER," +
                "watched_date DATE" +
                ")";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(createTableQuery)) {
            statement.execute();
        }
    }

    /**
     * Drop the 'movies' table if it exists.
     *
     * @throws SQLException If a database error occurs.
     */
    private static void dropMoviesTableIfExists() throws SQLException {
        String dropTableQuery = "DROP TABLE IF EXISTS movies";
        try (Statement statement = DatabaseConnection.getConnection().createStatement()) {
            statement.execute(dropTableQuery);
        }
    }
}
