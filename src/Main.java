import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1 . Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    LocalDate dueDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
                    Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());
                    taskManager.addTask(title, description, dueDate, priority);
                    break;

                case 2:
                    List<Task> tasks = taskManager.listTasks();
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    break;

                case 3:
                    System.out.print("Enter task ID to mark as complete: ");
                    int completeId = scanner.nextInt();
                    taskManager.markTaskAsComplete(completeId);
                    break;

                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    taskManager.deleteTask(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
