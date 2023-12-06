package daos;

import models.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {
    private static BookDAO bookDAO;
    private Book testBook;


    @BeforeAll
    static void init(){
        bookDAO =  new BookDAO();
    }
    @BeforeEach
    @Test
    void addBook() {
        // Create a test book to use in the tests


        testBook = new Book("Test Title", "Test First Name", "Test Surname", "123456789", LocalDate.now(), "Test Genre", "Test Publisher", "Available", "New");
        assertTrue(bookDAO.addBook(testBook), "Book should be added successfully");
    }




    @Test
    void updateBookAvailabilityStatus() {
        // Step 1: Add a test book
        Book testBook = new Book("jean daly", "Test First Name", "Test Surname", "1234567890", LocalDate.now(), "Test Genre", "Test Publisher", "Available", "New");
        assertTrue(bookDAO.addBook(testBook), "Book should be added successfully");

        // Step 2: Update the availability status of the test book
        String newStatus = "Checked Out";
        assertTrue(bookDAO.updateBookAvailabilityStatus(testBook.getBookID(), newStatus), "Availability status should be updated successfully");

        // Step 3: Retrieve the updated book
        Book updatedBook = bookDAO.getBook(testBook.getBookID());
        assertNotNull(updatedBook, "Updated book should not be null");

        // Step 4: Assert that the availability status has been updated
        assertEquals(newStatus, updatedBook.getAvailabilityStatus(), "Availability status should match the updated value");

        // Step 5: Clean up - delete the test book
        assertTrue(bookDAO.deleteBook(testBook.getBookID()), "Book should be deleted successfully");
    }

    @Test
    void getBook() {
        //Retrieve the book by ID and verify it's the same as the test book
        Book retrievedBook = bookDAO.getBook(testBook.getBookID());
        assertNotNull(retrievedBook, "Retrieved book should not be null");
        assertEquals(testBook.getTitle(), retrievedBook.getTitle(), "Titles should match");
        // Add more assertions for other fields
    }


    @Test
    void updateBook() {
        // Update some information of the test book
        testBook.setTitle("Updated Title");
        assertTrue(bookDAO.updateBook(testBook), "Book should be updated successfully");

        // Retrieve the book again and verify the updates
        Book updatedBook = bookDAO.getBook(testBook.getBookID());
        assertNotNull(updatedBook, "Updated book should not be null");
        assertEquals("Updated Title", updatedBook.getTitle(), "Updated title should match");
        // Add more assertions for other fields if necessary
    }


    @Test
    void deleteBook() {
        // Delete the test book
        assertTrue(bookDAO.deleteBook(testBook.getBookID()), "Book should be deleted successfully");

        // Try to retrieve the deleted book and verify that it's null
        Book deletedBook = bookDAO.getBook(testBook.getBookID());
        assertNull(deletedBook, "Deleted book should not be retrievable");
    }
//    @AfterEach
//    void tearDown() {
//        // Attempt to delete the test book to clean up the database after each test
//        bookDAO.deleteBook(testBook.getBookID());
//    }


}