package chattycharlie.task;

import chattycharlie.commands.CommandType;

import java.time.LocalDate;

/**
 * Represents an abstract task in the ChattyCharlie application.
 * Each task has a description, a status indicating if it's done, and a command type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected CommandType type;

    /**
     * Constructs a <code>Task</code> with the specified description and type.
     *
     * @param description the description of the task.
     * @param type the command type associated with the task.
     */
    public Task(String description, CommandType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Gets the marked status of the task.
     *
     * @return <code>"X"</code> if the task is done, otherwise a blank space.
     */
    public String getMarkedStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the done status of the task.
     *
     * @return <code>true</code> if the task is done, <code>false</code> otherwise.
     */
    public boolean getIsDoneStatus() {
        return this.isDone;
    }

    /**
     * Gets the command type of the task.
     *
     * @return the <code>CommandType</code> of the task.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true; //change the variable
    }

    /**
     * Unmarks the task, setting it as incomplete.
     */
    public void unmarkTask() {
        this.isDone = false; //change the variable
    }

    /**
     * Returns a string representation of the task, including its marked status and description.
     *
     * @return a string representation of the task.
     */
    public String toString() {
        return "[" + getMarkedStatus() + "] " + description;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving.
     *
     * @return a string representation of the task formatted for saving.
     */
    public String toSaveFormat() {
        return "[" + getMarkedStatus() + "] " + description;
    }

}