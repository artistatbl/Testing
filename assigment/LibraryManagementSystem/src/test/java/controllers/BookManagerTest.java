package controllers;


import daos.BookDAO;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class BookManagerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new main.Main().start(stage);
    }


    @Test
    public void testAddBookButton_shouldAddBookToGUI() {
        // Assuming your add book button has a fx:id "addBookButton"
        clickOn("#addBookButton");
        clickOn(1700, 400);

        // Fill in the details for the new book
        // Replace these with the correct fx:id of each field
        clickOn("#titleField").write("Sample Book Title");
        clickOn("#authorFirstNameField").write("John");
        clickOn("#authorSurnameField").write("Doe");
        clickOn("#isbnField").write("1234567890");
        //clickOn("#publishDateField").write("2023-01-01"); // Adjust date format if needed
        interact(()-> {
            DatePicker publishDateField = lookup("#publishDateField").queryAs(DatePicker.class);
            publishDateField.setValue(LocalDate.of(2023, 11, 29));

        });
        clickOn("#genreField").write("Fiction");
        clickOn("#publisherNameField").write("Sample Publisher");
        clickOn("#availabilityStatusField").write("Available");
        clickOn("#conditionField").write("New");

        // Assuming the save button has a fx:id "saveButton"
//       clickOn("#Shandlesave");
        clickOn(1250,780);
        sleep(1000);

        // Verifying that a new book entry was created
        // Replace with actual logic to verify that the book was added, possibly by checking a TableView or other UI element
        // Example: verifyThat("#bookTableView", hasItems(1)); // if you're using a TableView with fx:id "bookTableView"
        // For now, as a placeholder:



    }

    @DisplayName("Test Edit Book")
    @Test
    public void testEditBook_ShouldEditBookToGUI(){
        TableView<Book> bookTableView  = lookup("#booksTable").queryAs(TableView.class);
        assertTrue(bookTableView.getItems().size() > 0, "Book table manager should have at least one book for editing");
        Node firstRowCell = lookup(".table-row-cell").queryAll().iterator().next();
        clickOn(firstRowCell);

        //Click the 'edit book' button
        clickOn("#editBookButton");

        //Simulate editing book details in the book-table
        clickOn("#titleField").write("Sample Book Title");
        clickOn("#authorFirstNameField").write("John");
        clickOn("#authorSurnameField").write("Doe");
        clickOn("#isbnField").write("1234567890");

        //Click on the save button
        clickOn(1250, 750);

        sleep(1000);



    }
    @DisplayName("Test Delete Book")
    @Test
    public void testDeleteBookButton_ShouldRemoveBookFromGUI (){
        TableView<Book> bookTableView  = lookup("#booksTable").queryAs(TableView.class);
        assertFalse(bookTableView.getItems().isEmpty(), "Book table manager should have at least one book for editing");

        //Select the first row in the table
        Node firstRowCell = lookup(".table-row-cell").queryAll().iterator().next();
        clickOn(firstRowCell);

        //Store the book to be deleted
        Book bookToDelete = bookTableView.getSelectionModel().getSelectedItem();
        //Click the delete button
        clickOn("#deleteBookButton");

        clickOn(ButtonType.OK.getText());

        sleep(1000);

        assertFalse(bookTableView.getItems().contains(bookToDelete),"");

        BookDAO bookDAO = new BookDAO(); // Create an instance of BookDAO
        List<Book> booksFromDatabase = bookDAO.getAllBooks(); // Use the instance to call getAllBooks
        assertFalse(booksFromDatabase.contains(bookToDelete), "Book should not be in the database after deletion");
    }
}
