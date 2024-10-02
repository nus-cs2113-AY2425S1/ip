package wildpeace.task;

import wildpeace.exceptions.InvalidInputException;
import java.util.ArrayList;

/**
 * Manages a list of tasks. Provides operations to add, delete, mark, and unmark tasks.
 * Also prevents duplicate tasks from being added and provides a method to list all tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an existing list of tasks.
     *
     * @param tasks List of tasks to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list if it is not a duplicate.
     *
     * @param task The task to be added.
     * @throws InvalidInputException If the task already exists in the task list.
     */
    public void addTask(Task task) throws InvalidInputException {
        if (isDuplicate(task)) {
            throw new InvalidInputException("This task already exists in your list: " + task.getDescription());
        }
        tasks.add(task);
        System.out.println("Added: " + task);
    }

    /**
     * Checks if a task is a duplicate by comparing its description to the descriptions
     * of tasks already in the list.
     *
     * @param newTask The task to check for duplication.
     * @return true if the task already exists in the list, false otherwise.
     */
    private boolean isDuplicate(Task newTask) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(newTask.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted (0-based).
     * @throws InvalidInputException If the index is invalid or out of bounds.
     */
    public void deleteTask(int index) throws InvalidInputException {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Removed: " + removedTask);
        } else {
            throw new InvalidInputException("Invalid task number.");
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked (0-based).
     * @throws InvalidInputException If the index is invalid or out of bounds.
     */
    public void markTask(int index) throws InvalidInputException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.mark();
            System.out.println("Marked as done: " + task);
        } else {
            throw new InvalidInputException("Invalid task number.");
        }
    }

    /**
     * Unmarks a task at the specified index as not done.
     *
     * @param index The index of the task to be unmarked (0-based).
     * @throws InvalidInputException If the index is invalid or out of bounds.
     */
    public void unmarkTask(int index) throws InvalidInputException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.unmark();
            System.out.println("Unmarked: " + task);
        } else {
            throw new InvalidInputException("Invalid task number.");
        }
    }

    /**
     * Lists all tasks in the task list, along with their indices.
     * If the list is empty, a message indicating that no tasks are found will be displayed.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ": " + tasks.get(i));
            }
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
