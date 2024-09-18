import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks;
    private TaskStorage taskStorage;

    public TaskManager() {
        taskStorage = new TaskStorage();
        tasks = taskStorage.loadTasks();  // Load tasks from storage
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskStorage.saveTasks(tasks);  // Save tasks after adding
    }

    public void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks.get(index).getDescription());
            taskStorage.saveTasks(tasks);  // Save changes
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks.get(index).getDescription());
            taskStorage.saveTasks(tasks);  // Save changes
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void showTasks() {
        System.out.println("_______________________________________");
        if (tasks.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("_______________________________________");
    }

    public List<Task> getTasks() {
        return tasks;
    }
}