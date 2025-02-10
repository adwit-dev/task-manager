import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private boolean isCompleted;

    public Task(int id, String title, String description, LocalDate dueDate, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }
    public void markAsCompleted() { this.isCompleted = true; }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Due: %s | Priority: %s | Completed: %s",
                id, title, dueDate, priority, isCompleted);
    }
}

