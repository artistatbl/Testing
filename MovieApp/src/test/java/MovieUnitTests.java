import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MovieUnitTests {

    @BeforeEach
    void setup() {

    }

    @Test
    public void testCreateMovieWithValidPastDate_ShouldCreateMovieObject() {
        LocalDate pastDate = LocalDate.now().minusDays(1); // Create a date in the past
        try {
            Movie movie = new Movie("Title", "Director", 2020, "Genre", 4, pastDate);
            // Creating a Movie object with a past date should not throw an exception
            assertNotNull(movie);
        } catch (IllegalArgumentException e) {
            fail("Unexpected IllegalArgumentException: " + e.getMessage());
        }
    }

    @Test
    public void testCreateMovieWithFutureDate_ShouldRaiseIllegalArgumentException() {
        LocalDate futureDate = LocalDate.now().plusDays(1); // Create a date in the future
        try {
            Movie movie = new Movie("Title", "Director", 2023, "Genre", 5, futureDate);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid watched date: must not be in the future.", e.getMessage());
        }
    }

}
