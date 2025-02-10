import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
        loadTasksFromFile("tasks.txt");
    }

    public void addTask(String title, String description, LocalDate dueDate, Priority priority) {
        Task task = new Task(nextId++, title, description, dueDate, priority);
        tasks.add(task);
        saveTasksToFile("tasks.txt");
    }

    public List<Task> listTasks() {
        return tasks;
    }

    public void markTaskAsComplete(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markAsCompleted();
                saveTasksToFile("tasks.txt");
                break;
            }
        }
    }

}
