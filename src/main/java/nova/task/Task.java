package nova.task;

import nova.Ui;


import java.time.LocalDate;

/**
 * Represents a generic task with a description and a completion status.
 * Provides methods to mark tasks as done, retrieve task information, and manage the total number of tasks.
 */
public class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task (true if done, false otherwise).
     */
    protected boolean isDone;

    /**
     * Divider used in the task storage format.
     */
    public static final String DIVIDER = " | ";

    /**
     * Tracks the total number of tasks.
     */
    private static int numberOfTasks = 0;

    /**
     * Constructs a new task with the given description. The task is initially not done.
     * Increments the total number of tasks.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return The number of tasks.
     */
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Decreases the task count by one, typically used when a task is removed.
     */
    public static void removeTask() {
        numberOfTasks--;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * If the task is done, it returns "X". If not, it returns a blank space.
     *
     * @return The status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Displays an acknowledgement message using the provided message and the current number of tasks.
     *
     * @param message The message to be displayed.
     */
    public void printAcknowledgementMessage(String message) {
        Ui.displayAcknowledgementMessage(message, numberOfTasks);
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return The task information as a string.
     */
    public String getTaskInfo() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task for storage purposes.
     * This method is expected to be overridden by subclasses for specific task types.
     *
     * @return A string representing the task for storage.
     */
    public String getTaskStorageInfo() {
        return "";
    }


    public boolean isDate(LocalDate date) {
        return false;
    }

    public boolean doesContain(String phrase) {
        return description.contains(phrase);
    }

}
