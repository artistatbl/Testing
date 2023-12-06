import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Task;
import javafx.scene.Node;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import database.TaskDAO;


class TaskManagerTest extends ApplicationTest {


    private TaskDAO taskDao;


    @Override
    public void start(Stage stage) throws Exception {
        // Ensure MainApp properly loads the scene with your FXML layout
        taskDao = new TaskDAO(); // Initialize the TaskDAO
        new MainApp().start(stage);
    }

    @Test
    public void testAddTaskButton_ShouldAddTaskToGUI() {
        // Make sure the "Add Task" button opens the dialog with the form
        clickOn("#addButton");

        // Enter task details
        clickOn("#nameField").write("New Task");
        clickOn("#descriptionField").write("New task description");
        clickOn("#statusField").clickOn("Doing"); // Adjust if your ComboBox has different items

        // Set the due date on the JavaFX thread
        interact(() -> {
            DatePicker dueDateField = lookup("#dueDateField").queryAs(DatePicker.class);
            dueDateField.setValue(LocalDate.of(2023, 4, 1));
        });

        // Select the priority
        clickOn("#priorityField").clickOn("High"); // Adjust if your ComboBox has different items

        // Save the new task
        clickOn("#saveButton");

        // Wait for the UI to update (consider more robust wait mechanisms)
        sleep(1000);

        // Verify the task is added
        TableView<Task> taskTable = lookup("#taskTable").queryAs(TableView.class);
        assertFalse(taskTable.getItems().isEmpty(), "Task table should not be empty after adding a task.");

        // Assuming the new task is at the end of the list
        Task addedTask = taskTable.getItems().get(taskTable.getItems().size() - 1);
        assertEquals("New Task", addedTask.getName(), "Task name should match.");
        assertEquals("New task description", addedTask.getDescription(), "Task description should match.");
        assertEquals("Doing", addedTask.getStatus(), "Task status should match.");
        assertEquals(LocalDate.of(2023, 4, 1), addedTask.getDueDate(), "Task due date should match.");
        assertEquals("High", addedTask.getPriority(), "Task priority should match.");
    }

    // Additional test methods...

    @Test
    public void testDeleteTaskButton_ShouldRemoveTaskFromGUI() {
        // Select the first task in the table
        TableView<Task> taskTable = lookup("#taskTable").queryAs(TableView.class);
        assertFalse(taskTable.getItems().isEmpty(),"task table should not have a task after deleting a task");
        // Select the first row in the table by clicking on the first cell
        Node firstRowCell = lookup(".table-row-cell").queryAll().iterator().next();
        clickOn(firstRowCell);

        // Store the details of the task to be deleted for verification later
        Task taskToDelete = taskTable.getSelectionModel().getSelectedItem();

        // Click the "Delete Task" button
        clickOn("#deleteButton");

        // Wait for the UI to update (consider more robust wait mechanisms)
        sleep(1000);

        // Verify the task is removed from the table
        assertFalse(taskTable.getItems().contains(taskToDelete), "Task should be removed from the table.");
        List<Task> tasksFromDatabase = taskDao.getAllTasks();
        assertFalse(tasksFromDatabase.contains(taskToDelete), "Task should be removed from the data model.");
    }

    @Test
    public void testEditTask() {
        // Select the first task in the table
        TableView<Task> taskTable = lookup("#taskTable").queryAs(TableView.class);
        assertTrue(taskTable.getItems().size() > 0, "Task table should have at least one task for editing");
        Node firstRowCell = lookup(".table-row-cell").queryAll().iterator().next();
        clickOn(firstRowCell);

        // Click the "Edit Task" button
        clickOn("#editButton");

        // Simulate editing task details in the dialog
        // clickOn("#nameField").write("Updated Name");
        clickOn("#descriptionField").write("Updated Description");
        clickOn("#statusField").clickOn("Doing");
        clickOn("#priorityField").clickOn("High");

        // Click the save button to apply changes
        clickOn("#saveButton");

        // Wait for the UI to update (consider more robust wait mechanisms)
        sleep(1000);

        // Verify the task has been updated in the table
        Task editedTask = taskTable.getSelectionModel().getSelectedItem();
        assertEquals("Updated Name", editedTask.getName(), "Task name should be updated.");
        assertEquals("Updated Description", editedTask.getDescription(), "Task description should be updated.");
        assertEquals("Doing", editedTask.getStatus(), "Task status should be updated.");
        assertEquals("High", editedTask.getPriority(), "Task priority should be updated.");
    }

}
