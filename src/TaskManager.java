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

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        saveTasksToFile("tasks.txt");
    }

    public void saveTasksToFile(String filename) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Task task : tasks) {
                writer.write(String.format("%d,%s,%s,%s,%s,%b%n",
                        task.getId(), task.getTitle(), task.getDescription(),
                        task.getDueDate(), task.getPriority(), task.isCompleted()));
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String description = parts[2];
                LocalDate dueDate = LocalDate.parse(parts[3]);
                Priority priority = Priority.valueOf(parts[4]);
                boolean isCompleted = Boolean.parseBoolean(parts[5]);
                Task task = new Task(id, title, description, dueDate, priority);
                if (isCompleted) {
                    task.markAsCompleted();
                }
                tasks.add(task);
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }
}
