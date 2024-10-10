package bron.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, and find tasks, as well as print the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the given tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A TaskList containing all tasks that match the keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                results.addTask(task);
            }
        }
        return results;
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to delete.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        Task removedTask = tasks.remove(index);
        System.out.println("Deleted task: " + removedTask);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Prints all tasks in the list.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}