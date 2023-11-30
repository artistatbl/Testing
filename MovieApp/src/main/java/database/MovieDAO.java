package database;

import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing Movie data in the database.
 */
public class MovieDAO {

    /**
     * Add a new movie to the database.
     *
     * @param movie The movie to add.
     * @throws SQLException If a database error occurs.
     */
    public static void addMovie(Movie movie) throws SQLException {
        String insertQuery = "INSERT INTO movies (title, director, year, genre, rating, watched_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDirector());
            preparedStatement.setInt(3, movie.getYear());
            preparedStatement.setString(4, movie.getGenre());
            preparedStatement.setInt(5, movie.getRating());
            preparedStatement.setDate(6, java.sql.Date.valueOf(movie.getWatchedDate()));

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Delete a movie from the database by its ID.
     *
     * @param movieId The ID of the movie to delete.
     * @throws SQLException If a database error occurs.
     */
    public static void deleteMovie(int movieId) throws SQLException {
        String deleteQuery = "DELETE FROM movies WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, movieId);
            preparedStatement.executeUpdate();
        }
    }

    public static List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String selectQuery = "SELECT * FROM movies";

        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getInt("year"),
                        resultSet.getString("genre"),
                        resultSet.getInt("rating"),
                        resultSet.getDate("watched_date").toLocalDate()
                );
                movies.add(movie);
            }
        }

        return movies;
    }
}
