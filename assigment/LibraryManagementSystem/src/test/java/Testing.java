//import static org.junit.jupiter.api.Assertions.*;


import javafx.stage.Stage;
import main.Main;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;


class Testing extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }


    @Test
    public void testAddBookButton_shouldAddBookToGUI() {
        // Assuming your add book button has a fx:id "addBookButton"
        clickOn("#addBookButton");

        // Fill in the details for the new book
        // Replace these with the correct fx:id of each field
       // clickOn("#titleField").write("Sample Book Title");
        clickOn("#authorFirstNameField").write("John");
        clickOn("#authorSurnameField").write("Doe");
        clickOn("#isbnField").write("1234567890");
        clickOn("#publishDateField").write("2023-01-01"); // Adjust date format if needed
        clickOn("#genreField").write("Fiction");
        clickOn("#publisherNameField").write("Sample Publisher");
        clickOn("#availabilityStatusField").write("Available");
        clickOn("#conditionField").write("New");

        // Assuming the save button has a fx:id "saveButton"
        clickOn("#saveButton");
        sleep(1000);

        // Verifying that a new book entry was created
        // Replace with actual logic to verify that the book was added, possibly by checking a TableView or other UI element
        // Example: verifyThat("#bookTableView", hasItems(1)); // if you're using a TableView with fx:id "bookTableView"
        // For now, as a placeholder:


        // Cleanup: Close any open dialogs if necessary
       // FxToolkit.hideStage();
    }

    // Implement the isOkClicked method if it's not already part of your controller





}
