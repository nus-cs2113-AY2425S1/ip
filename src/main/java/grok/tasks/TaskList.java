package grok.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. Provides methods to add, remove, and access tasks,
 * as well as search for tasks by keyword.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Default constructor that initializes an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes the TaskList with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed from the list.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList based on the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword in their description.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
