package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Task;
import database.TaskDAO;
import javafx.collections.FXCollections;

import java.io.IOException;

public class MainViewController {

    public Button addButton;
    public Button editButton;
    public Button deleteButton;
    public Button completeButton;
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Integer> idColumn;
    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> statusColumn;
    @FXML
    private TableColumn<Task, String> dueDateColumn;
    @FXML
    private TableColumn<Task, String> priorityColumn;
    @FXML
    private TableColumn<Task, String> creationDateColumn;
    private Stage mainStage;

    @FXML
    public void initialize() {
        // Configure columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        setStatusCellFactory();
        setPriorityCellFactory();

        loadTasks();
    }

    private void loadTasks() {
        TaskDAO taskDAO = new TaskDAO();
        taskTable.setItems(FXCollections.observableArrayList(taskDAO.getAllTasks()));
    }

    @FXML
    private void showAddTaskDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddTaskDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(taskTable.getScene().getWindow()); // Assumes taskTableView is part of the primary stage
            dialogStage.setScene(new Scene(root));

            AddTaskDialogController dialogController = loader.getController();
            dialogController.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            loadTasks(); // Refresh tasks list after adding new task
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showEditTaskDialog() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditTaskDialog.fxml"));
                Parent root = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Task");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(taskTable.getScene().getWindow());
                dialogStage.setScene(new Scene(root));

                EditTaskWindowController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setTask(selectedTask);

                dialogStage.showAndWait();

                loadTasks(); // Reload tasks to update the TableView
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception
            }
        } else {
            showAlert("No Task Selected", "Please select a task to edit.");
        }
    }

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            TaskDAO taskDAO = new TaskDAO();
            taskDAO.deleteTask(selectedTask.getId());

            loadTasks(); // Refresh the task list
        } else {
            // Show an alert informing that no task is selected
            showAlert("No Task Selected", "Please select a task to delete.");
        }
    }
    @FXML
    private void handleCompleteTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            TaskDAO taskDAO = new TaskDAO();
            taskDAO.updateTaskStatus(selectedTask.getId(), "Done");

            loadTasks(); // Refresh the task list
        } else {
            // Show an alert informing that no task is selected
            showAlert("No Task Selected", "Please select a task to mark as complete.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setStatusCellFactory() {
        statusColumn.setCellFactory(column -> new TableCell<Task, String>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);

                if (status == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);
                    setStyle("-fx-background-color: " + getStatusColor(status));
                }
            }
        });
    }

    private String getStatusColor(String status) {
        switch (status) {
            case "To Do":
                return "#D3D3D3"; // Grey
            case "Doing":
                return "#FFFFE0"; // Light Yellow
            case "Done":
                return "#90EE90"; // Light Green
            default:
                return "";
        }
    }

    private void setPriorityCellFactory() {
        priorityColumn.setCellFactory(column -> new TableCell<Task, String>() {
            @Override
            protected void updateItem(String priority, boolean empty) {
                super.updateItem(priority, empty);

                if (priority == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(priority);
                    setStyle("-fx-background-color: " + getPriorityColor(priority));
                }
            }
        });
    }

    private String getPriorityColor(String priority) {
        switch (priority) {
            case "Low":
                return "#90EE90"; // Light Green
            case "Normal":
                return "#FFFFE0"; // Light Yellow
            case "High":
                return "#FF6347"; // Light Red
            default:
                return "";
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
