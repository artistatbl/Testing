package controller;

import database.MovieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Movie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieAppController {


    @FXML
    private Button addMovieButton;
    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    public TableColumn<Movie, Integer> idColumn;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, String> directorColumn;
    @FXML
    private TableColumn<Movie, Integer> yearColumn;
    @FXML
    private TableColumn<Movie, String> genreColumn;
    @FXML
    private TableColumn<Movie, Integer> ratingColumn;
    @FXML
    private TableColumn<Movie, String> watchedDateColumn;

    private final ObservableList<Movie> movieList = FXCollections.observableArrayList();

    // Initialize method (you may need to adjust this according to your FXML file)
    public void initialize() {
        // Bind table columns to model properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
//        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
//        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
//        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty().asString());
//        watchedDateColumn.setCellValueFactory(cellData -> cellData.getValue().watchedDateProperty());

        // Set the items of the TableView to the observable movieList
        movieTableView.setItems(movieList);

        // Retrieve movies from the database and populate the table
        try {
            List<Movie> movies = MovieDAO.getAllMovies();
            movieList.addAll(movies);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database retrieval error
        }
    }

    // Add Movie Button Clicked
    @FXML
    private void addMovieButtonClicked() {
        try {
            // Load the "Add Movie" window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddMovie.fxml"));
            Stage addMovieStage = new Stage();
            addMovieStage.initModality(Modality.APPLICATION_MODAL);
            addMovieStage.initOwner((Window) addMovieButton.getScene().getWindow());
            addMovieStage.setScene(new Scene(loader.load()));

            // Set the controller for the "Add Movie" window
            AddMovieController addMovieController = loader.getController();
            addMovieController.setMovieList(movieList); // Pass the movieList from the main controller

            addMovieStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle window loading error
        }
    }



    // Delete Movie Button Clicked
    @FXML
    private void deleteMovieButtonClicked() {
        // Get the selected movie from the TableView
        Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            try {
                // Delete the selected movie from the database
                MovieDAO.deleteMovie(selectedMovie.getId());

                // Remove the selected movie from the TableView
                ObservableList<Movie> movieList = movieTableView.getItems();
                movieList.remove(selectedMovie);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database deletion error
            }
        }
    }
}
