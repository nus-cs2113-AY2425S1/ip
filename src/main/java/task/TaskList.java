package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Default constructor that initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes the TaskList with a list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
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
     * Retrieves all tasks in the list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain a given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}