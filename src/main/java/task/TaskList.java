package task;

import java.util.ArrayList;

/**
 * Contains a list of tasks and methods to manipulate the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with the given tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param index The index of the task to remove.
     * @return The Task object that was removed from the list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list contains no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks in the task list.
     *
     * @return An ArrayList containing the Task objects in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
