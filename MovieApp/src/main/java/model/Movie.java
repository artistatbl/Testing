package model;

import java.time.LocalDate;

/**
 * Represents a movie in the movie database.
 */
public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;
    private String genre;
    private int rating;
    private LocalDate watchedDate;

    /**
     * Constructs a new Movie object with default values.
     */
    public Movie() {
        // Default constructor
    }

    /**
     * Constructs a new Movie object with the provided details.
     *
     * @param id          The unique identifier for the movie.
     * @param title       The title of the movie.
     * @param director    The director of the movie.
     * @param year        The year the movie was released.
     * @param genre       The genre of the movie.
     * @param rating      The user rating for the movie.
     * @param watchedDate The date when the movie was watched.
     */
    public Movie(int id, String title, String director, int year, String genre, int rating, LocalDate watchedDate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        setWatchedDate(watchedDate); // Call the setter with validation
    }

    /**
     * Constructs a new Movie object with the provided details.
     *
     * @param title       The title of the movie.
     * @param director    The director of the movie.
     * @param year        The year the movie was released.
     * @param genre       The genre of the movie.
     * @param rating      The user rating for the movie.
     * @param watchedDate The date when the movie was watched.
     */
    public Movie(String title, String director, int year, String genre, int rating, LocalDate watchedDate) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.watchedDate = watchedDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * Get the date when the movie was watched.
     *
     * @return The date when the movie was watched.
     */
    public LocalDate getWatchedDate() {
        return watchedDate;
    }

    /**
     * Set the date when the movie was watched.
     * Validation is added to ensure that a future date cannot be set.
     *
     * @param watchedDate The date when the movie was watched.
     */
    public void setWatchedDate(LocalDate watchedDate) {
        LocalDate currentDate = LocalDate.now();
        if (watchedDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("Watched date cannot be in the future.");
        }
        this.watchedDate = watchedDate;
    }

    // Additional methods or overrides can be added as needed.
}
