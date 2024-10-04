package yapper.tasks;

import yapper.io.StringStorage;

/**
 * The basic template for tasks in Yapper.
 *
 * <p>
 * The Task class represents a single task with a description and
 * completion status. It provides methods to manage task details
 * and facilitate task display and conversion for saving/loading.
 * <p/>
 *
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String taskDesc;
    /**
     * The completion status of the task.
     * It is either done or not done.
     */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and
     * initializes its completion status to false (not done).
     *
     * <p> Used when reading from user input. </p>
     *
     * @param taskDesc the description of the task
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }
    /**
     * Constructs a Task with the specified description and completion status.
     *
     * <p> Used when reading from file. </p>
     *
     * @param taskDesc the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String taskDesc, boolean isDone) {
        this.taskDesc = taskDesc;
        this.isDone = isDone;
    }

    /**
     * Returns the task description
     *
     * @return the task description
     */
    public String getDesc() { return taskDesc; }
    public void setDesc(String taskDesc) { this.taskDesc = taskDesc; }
    /**
     * Returns the completion status symbol of the task.
     *
     * @return a symbol representing the completion status
     */
    public String getDoneStatus() {
        return isDone ? StringStorage.SYMBOL_IS_DONE : StringStorage.SYMBOL_NOT_DONE;
    }
    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done; false otherwise
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Sets the completion status of the task.
     *
     * @param isDone the new completion status
     */
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }


    /**
     * Converts the task to a string format for display.
     *
     * @return a formatted string showing the task's status and description.
     */
    public String taskToDisplay() {
        return "[" + getDoneStatus() + "] " + taskDesc;
    }
    /**
     * Converts the task to a string format for writing to / reading from a file.
     *
     * @return a formatted string representing the task's status and description.
     */
    public String taskToString() {
        return getDoneStatus() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + taskDesc;
    }
}
