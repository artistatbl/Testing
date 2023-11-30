package controller;


import database.MovieDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Movie;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddMovieController {
    @FXML
    private TextField titleField;
    @FXML
    private TextField directorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField ratingField;
    @FXML
    private TextField watchedDateField;
    @FXML
    private Button saveButton;

    private ObservableList<Movie> movieList; // Reference to the movie list from the main controller

    @FXML
    private void saveButtonClicked() {
        // Retrieve user-entered movie details
        String title = titleField.getText();
        String director = directorField.getText();
        int year = Integer.parseInt(yearField.getText());
        String genre = genreField.getText();
        int rating = Integer.parseInt(ratingField.getText());
        LocalDate watchedDate = LocalDate.parse(watchedDateField.getText()); // Assuming user enters date in a valid format

        // Create a new Movie object
        Movie newMovie = new Movie(title, director, year, genre, rating, watchedDate);

        try {
            // Add the new movie to the database
            MovieDAO.addMovie(newMovie);

            // Add the new movie to the movie list (to update the main GUI table)
            movieList.add(newMovie);

            // Close the "Add Movie" window
            saveButton.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database insertion error
        }
    }
    // Set the movie list from the main controller
    public void setMovieList(ObservableList<Movie> movieList) {
        this.movieList = movieList;
    }
    // Other methods...
}

