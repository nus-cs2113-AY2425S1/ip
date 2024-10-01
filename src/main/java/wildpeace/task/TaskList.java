package wildpeace.task;

import wildpeace.exceptions.InvalidInputException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added: " + task);
    }

    public void deleteTask(int index) throws InvalidInputException {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Removed: " + removedTask);
        } else {
            throw new InvalidInputException("Invalid task number.");
        }
    }

    public void markTask(int index) throws InvalidInputException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.mark();
            System.out.println("Marked as done: " + task);
        } else {
            throw new InvalidInputException("Invalid task number.");
        }
    }

    public void unmarkTask(int index) throws InvalidInputException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.unmark();
            System.out.println("Unmarked: " + task);
        } else {
            throw new InvalidInputException("Invalid task number.");
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ": " + tasks.get(i));
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
