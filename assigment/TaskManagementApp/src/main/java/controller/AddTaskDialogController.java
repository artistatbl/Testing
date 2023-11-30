package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Task;
import database.TaskDAO;
import java.time.LocalDate;

public class AddTaskDialogController {
    public Button saveButton;
    public Button cancelButton;
    private Stage dialogStage;

    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private ComboBox<String> statusField;
    @FXML private DatePicker dueDateField;
    @FXML private ComboBox<String> priorityField;
    @FXML private DatePicker creationDateField;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void saveTask() {
        // Create a new Task object from the input fields
        Task newTask = new Task();
        newTask.setName(nameField.getText());
        newTask.setDescription(descriptionField.getText());
        newTask.setStatus(statusField.getValue());
        newTask.setDueDate(dueDateField.getValue());
        newTask.setPriority(priorityField.getValue());
        newTask.setCreationDate(LocalDate.now());

        // Use TaskDAO to save the new task
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.addTask(newTask);

        // Close the dialog
        dialogStage.close();
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }
}
