package main.java;

import ran.task.Task;
import ran.exception.OutOfListBoundsException;
import java.util.ArrayList;

/**
 * A class that contains an arraylist of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructor of a <code>TaskList</code> object.
     * Initialises an empty arraylist with a taskCount of 0.
     */
    public TaskList() {
        taskCount = 0;
        tasks = new ArrayList<>();
    }

    /**
     * Getter for taskCount.
     *
     * @return Integer representing number of tasks in the list
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Getter for the arraylist.
     *
     * @returns ArrayList representing list of all tasks in the <code>TaskList</code>
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Checks if index is within bounds of the list
     *
     * @param index Integer passed is as index to be checked
     * @throws OutOfListBoundsException If index is out of bounds of the list
     */
    private void checkIndexOutOfBounds(int index) throws OutOfListBoundsException {
        if (index >= taskCount || index < 0) {
            throw new OutOfListBoundsException();
        }
    }

    /**
     * Getter for returning a specific task in the list based on an index.
     *
     * @param index Integer passed as an index to retrieve task
     * @return Task based on the <code>index</code>
     * @throws OutOfListBoundsException If index is out of bounds of the list
     */
    public Task getTask(int index) throws OutOfListBoundsException {
        checkIndexOutOfBounds(index);
        return tasks.get(index);
    }

    /**
     * Remove a specific task in the list based on an index.
     *
     * @param index Integer passed as an index to delete task
     * @return Task that has been deleted
     * @throws OutOfListBoundsException If index is out of bounds of the list
     */
    public Task removeTask(int index) throws OutOfListBoundsException {
        checkIndexOutOfBounds(index);
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        taskCount--;
        return deletedTask;
    }

    /**
     * Add a new task to the list.
     *
     * @param newTask Task being added to the list
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        taskCount++;
    }
}
