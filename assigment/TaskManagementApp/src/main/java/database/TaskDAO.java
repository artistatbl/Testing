package database;

import model.Task;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public void addTask(Task task) {
        String sql = "INSERT INTO Tasks (Name, Description, Status, DueDate, Priority, CreationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStatus());
            pstmt.setString(4, task.getDueDate().toString());
            pstmt.setString(5, task.getPriority());
            pstmt.setString(6, task.getCreationDate().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding task: " + e.getMessage());
        }
    }

    public void updateTask(Task task) {
        String sql = "UPDATE Tasks SET " +
                "Name = ?, " +
                "Description = ?, " +
                "Status = ?, " +
                "DueDate = ?, " +
                "Priority = ? " +
                "WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStatus());
            pstmt.setString(4, task.getDueDate() != null ? task.getDueDate().toString() : null);
            pstmt.setString(5, task.getPriority());
            pstmt.setInt(6, task.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Tasks";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("Name"));
                task.setDescription(rs.getString("Description"));
                task.setStatus(rs.getString("Status"));
                task.setPriority(rs.getString("Priority"));

                String dueDateString = rs.getString("DueDate");
                if (dueDateString != null) {
                    task.setDueDate(LocalDate.parse(dueDateString));
                }

                String creationDateString = rs.getString("CreationDate");
                if (creationDateString != null) {
                    task.setCreationDate(LocalDate.parse(creationDateString));
                }

                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void deleteTask(int taskId) {
        String sql = "DELETE FROM Tasks WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        String sql = "UPDATE Tasks SET Status = ? WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating task status: " + e.getMessage());
        }
    }
}
