package niwa.data.task;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a list of tasks.
 * It provides methods to add, delete, find, and manage tasks.
 * This class follows the Singleton design pattern to ensure only one instance exists throughout the application.
 */
public class TaskList {
    private static TaskList instance; // Singleton instance
    private ArrayList<Task> tasks; // List to hold tasks

    // Private constructor to prevent instantiation
    private TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of {@code TaskList}.
     *
     * @return The singleton {@code TaskList} instance.
     */
    public static synchronized TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList(); // Create a new instance if none exists
        }
        return instance;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Sets the tasks in the task list.
     *
     * @param tasks The input list of tasks to set.
     */
    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return The size of the list of tasks as an int.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @throws NiwaDuplicateTaskException If the current list contains the task.
     */
    public void addTask(Task task) throws NiwaDuplicateTaskException {
        if (isContainingDuplicate(task)) {
            throw new NiwaDuplicateTaskException();
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws NiwaTaskIndexOutOfBoundException If the index is out of bound.
     */
    public Task deleteTask(int index) throws NiwaTaskIndexOutOfBoundException {
        Task temp = findTask(index);
        tasks.remove(index);
        return temp; // Return the deleted task
    }

    /**
     * Clears the content of the task list.
     */
    public void clearTaskList() {
        tasks.clear();
    }

    /**
     * Finds a task in the task list by index.
     *
     * @param index The index of the task to find.
     * @return The task if found.
     * @throws NiwaTaskIndexOutOfBoundException If the index is out of bound.
     */
    public Task findTask(int index) throws NiwaTaskIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new NiwaTaskIndexOutOfBoundException(tasks.size());
        }

        return tasks.get(index);
    }

    /**
     * Checks for duplicate tasks in the task list.
     * The {@link Task#isSameTask} method is used for this comparison,
     * which defines a weaker notion of equality.
     *
     * @param task The task to check for duplicates.
     * @return true if a duplicate task exists; false otherwise.
     */
    public boolean isContainingDuplicate(Task task) {
        return tasks.stream()
                .filter((t) -> t.getType().equals(task.getType())) // Filter by task type
                .anyMatch(t -> t.isSameTask(task)); // Check for the same task
    }
}
