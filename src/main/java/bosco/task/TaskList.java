package bosco.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasksList;

    /**
     * Constructs this class with an empty list.
     */
    public TaskList() {
        tasksList = new ArrayList<>();
    }

    /**
     * Constructs this class with a list of existing tasks.
     *
     * @param tasks <code>ArrayList</code> of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasksList = tasks;
    }

    /**
     * Adds a task to this list.
     *
     * @param toAdd Task to be added.
     */
    public void addTask(Task toAdd) {
        tasksList.add(toAdd);
    }

    /**
     * Removes a task from this list.
     *
     * @param toRemove Task to be removed.
     */
    public void removeTask(Task toRemove) {
        tasksList.remove(toRemove);
    }

    /**
     * Gets the Task at the specified index.
     *
     * @param index Index of the task in the list.
     * @return The specified task.
     */
    public Task getTaskAtIndex(int index) {
        return tasksList.get(index);
    }

    /**
     * Gets the entire list of tasks.
     *
     * @return <code>ArrayList</code> of all the tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasksList);
    }

    /**
     * Gets the number of tasks in this list.
     *
     * @return Number of tasks.
     */
    public int getSize() {
        return tasksList.size();
    }
}
