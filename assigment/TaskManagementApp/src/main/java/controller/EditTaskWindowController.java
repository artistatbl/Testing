package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Task;
import database.TaskDAO;

public class EditTaskWindowController {

    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private ComboBox<String> statusField;
    @FXML private DatePicker dueDateField;
    @FXML private ComboBox<String> priorityField;
    // ... other fields

    private Task task;
    private Stage dialogStage;

    public void setTask(Task task) {
        this.task = task;
        populateFields();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void populateFields() {
        if (task != null) {
            nameField.setText(task.getName());
            descriptionField.setText(task.getDescription());
            statusField.setValue(task.getStatus());
            dueDateField.setValue(task.getDueDate());
            priorityField.setValue(task.getPriority());

        }
    }

    @FXML
    private void save() {
        // Collect data from fields and update task
        task.setName(nameField.getText());
        task.setDescription(descriptionField.getText());
        task.setStatus(statusField.getValue());
        task.setDueDate(dueDateField.getValue());
        task.setPriority(priorityField.getValue());

        TaskDAO taskDAO = new TaskDAO();
        taskDAO.updateTask(task);

        dialogStage.close();
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }

    // ... other methods
}
