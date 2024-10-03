package tars.tasklist;

import tars.tarsexception.tarsException;
import tars.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * The TaskList class provides methods to manage tasks, including adding,
 * retrieving, and deleting tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws tarsException If the index is out of range.
     */
    public Task getTask(int index) throws tarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new tarsException("Task number out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Deletes the task at the specified index and returns it.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws tarsException If the index is out of range.
     */
    public Task deleteTask(int index) throws tarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new tarsException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the total number of tasks in the TaskList.
     *
     * @return The total number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Checks if a task already exists in the TaskList.
     *
     * @param task The task to check.
     * @return True if the task exists, false otherwise.
     */
    public boolean hasTask(Task task) {
        for (Task existingTask : tasks) {
            if (existingTask.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
