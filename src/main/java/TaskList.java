import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks. Provides methods to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to delete.
     * @throws BebeException if the index is out of range.
     */
    public void deleteTask(int index) throws BebeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task number is out of range. Cannot delete task.");
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark as done.
     * @throws BebeException if the index is out of range.
     */
    public void markTask(int index) throws BebeException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task number is out of range. Cannot mark task.");
        }
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param index The index of the task to mark as not done.
     * @throws BebeException if the index is out of range.
     */
    public void unmarkTask(int index) throws BebeException {
        try {
            Task task = tasks.get(index);
            task.markAsNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task number is out of range. Cannot unmark task.");
        }
    }

    /**
     * Returns the total number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }
}