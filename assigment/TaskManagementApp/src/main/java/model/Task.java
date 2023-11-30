package model;

import java.time.LocalDate;

public class Task {

    private int id;
    private String name;
    private String description;
    private String status;
    private LocalDate dueDate;
    private String priority;
    private LocalDate creationDate;

    // Constructors
    public Task() {
    }

    public Task(int id, String name, String description, String status, LocalDate dueDate, String priority, LocalDate creationDate) {
        this(name, description, status, dueDate, priority, creationDate);
        this.id = id;
    }

    public Task(String name, String description, String status, LocalDate dueDate, String priority, LocalDate creationDate) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
