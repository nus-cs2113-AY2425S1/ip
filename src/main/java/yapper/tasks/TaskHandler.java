package yapper.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a collection of tasks for Yapper.
 *
 * <p>
 * The TaskHandler class manages a list of tasks, providing methods
 * to perform operations such as adding, deleting, and updating tasks.
 * <p/>
 *
 */
public class TaskHandler {
    /**
     * The collection of tasks.
     */
    private List<Task> tasks;


    public TaskHandler() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the list of all tasks managed by this TaskHandler.
     *
     * @return a List of Task objects
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
    /**
     * Returns the total number of current tasks.
     *
     * @return the number of tasks
     */
    public int getCurrTaskTotal() {
        return tasks.size();
    }

    /**
     * Returns true if list contains no elements, and false otherwise
     *
     * @return true if list contains no elements, and false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    /**
     * Retrieves a task based on its ordinal index.
     *
     * @param taskOrdinal the index of the task to retrieve
     * @return the Task at the specified index
     */
    public Task getTaskAtOrdinal(int taskOrdinal) {
        return tasks.get(taskOrdinal);
    }

    /**
     * Retrieves an ordinal of a Task.
     *
     * @param task the task at the ordinal to retrieve
     * @return the index of the specified task
     */
    public int getOrdinalOf(Task task) {
        return tasks.indexOf(task);
    }


    /**
     * Adds a new task to the list.
     *
     * @param task the Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Deletes a task from the list based on its ordinal index.
     *
     * @param taskOrdinal the index of the task to be deleted
     */
    public void deleteTask(int taskOrdinal) {
        tasks.remove(taskOrdinal);
    }
    /**
     * Updates the completion status of a specified task.
     *
     * @param task the Task to update
     * @param isDone the new completion status
     */
    public void updateTaskStatus(Task task, boolean isDone) {
        task.setDoneStatus(isDone);
    }

    /**
     * Converts the list of tasks into an {@code ArrayList} of string representations.
     * Each task is represented by its string format as defined by the {@code taskToString()} method.
     *
     * @return an {@code ArrayList<String>} containing the string representations of all tasks.
     */
    public ArrayList<String> tasksToString() {
        ArrayList<String> taskAsStrings = new ArrayList<>();
        for (Task task : this.tasks) {
            taskAsStrings.add(task.taskToString());
        }
        return taskAsStrings;
    }
}
