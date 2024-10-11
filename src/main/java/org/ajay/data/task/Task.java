package org.ajay.data.task;

import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.ui.TextUi;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    public final static String MARK_COMMAND = "mark";
    public final static String UNMARK_COMMAND = "unmark";
    public final static String LIST_COMMAND = "list";
    public final static String DELETE_COMMAND = "delete";

    public static final int COMMAND_LOAD_INDEX = 0;
    public static final int DONE_FLAG_LOAD_INDEX = 1;
    public static final int DESCRIPTION_LOAD_INDEX = 2;

    protected String description; // Description of the task
    private boolean isDone; // Status of the task

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     * @throws EmptyArgumentException If the description is empty.
     */
    public Task(String description) throws EmptyArgumentException {
        if (description == null || description.isEmpty()) {
            throw new EmptyArgumentException("Description cannot be empty. " + Error.EMPTY_ARG.toString());
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the description of the task.
     *
     * @param newDescription New description of the task.
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Sets done state of the task.
     *
     * @param bool Done state of the task.
     */
    public void setDoneState(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Gets the done state of the task.
     *
     * @return Done state of the task.
     */
    public boolean getDoneState() {
        return this.isDone;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    /** Marks the task as done. */
    public void markAsDone() {
        this.isDone = true;

        TextUi.printSuccess("Nice! I've marked this task as done: \n  " + toString());
    }

    /** Marks the task as not done. */
    public void markAsUndone() {
        this.isDone = false;

        TextUi.printSuccess("OK, I've marked this task as not done yet: \n  " + this.toString());
    }

    /**
     * Prints the task to the console.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public abstract String saveTaskString();
}
