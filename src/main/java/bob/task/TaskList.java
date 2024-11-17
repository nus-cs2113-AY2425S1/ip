package bob.task;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialise the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The Task object that was removed from the list.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing all the Task objects in the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return An integer representing the size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }
}