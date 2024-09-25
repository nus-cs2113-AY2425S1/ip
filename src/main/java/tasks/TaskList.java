package tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks that Bento manages.
 * It provides methods to add, retrieve, delete, and update tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Updates the completion status of the task at the specified index.
     *
     * @param isDone The completion status to set (true if done, false otherwise).
     * @param index  The index of the task to update.
     */
    public void updateTask(boolean isDone, int index) {
        getTask(index).setDone(isDone);
    }

    /**
     * Returns the total number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if all tasks in the TaskList are marked as done.
     *
     * @return true if all tasks are done, false otherwise.
     */
    public boolean allDone() {
        for (Task task : tasks) {
            if (!task.isDone()) {
                return false;
            }
        }

        return true;
    }
}
