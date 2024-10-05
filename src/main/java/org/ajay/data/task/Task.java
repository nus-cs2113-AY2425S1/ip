package org.ajay.data.task;

import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;

public abstract class Task {
    public final static String MARK_COMMAND_STRING = "mark";
    public final static String UNMARK_COMMAND_STRING = "unmark";
    public final static String LIST_COMMAND_STRING = "list";
    public final static String DELETE_COMMAND_STRING = "delete";

    protected String description; // Description of the task
    private boolean isDone; // Status of the task

    // TODO: Possible to use arraylist length
    // private static int numberOfTasks = 0; // Number of tasks in the list

    /**
     * Constructor for the Task class.
     *
     * @param description
     */
    public Task(String description) throws EmptyArgumentException {

        if (description == null || description.isEmpty()) {
            throw new EmptyArgumentException("Description cannot be empty. " + Error.EMPTY_ARG.toString());
        }

        this.description = description;
        this.isDone = false;

        // numberOfTasks++;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the description of the task.
     *
     * @param newDescription
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Changes the done state of the task.
     *
     * @param bool
     */
    public void setDoneState(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns the done state of the task.
     *
     * @return done state of the task
     */
    public boolean getDoneState() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;

        System.out.println("Nice! I've marked this task as done: \n  " + toString());
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;

        System.out.println("OK, I've marked this task as not done yet: \n  " + this.toString());
    }

    // public static int setNumberOfTasks(int num) {
    // return numberOfTasks = num;
    // }

    /**
     * Prints the task to the console.
     *
     * @return task to be printed
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public abstract String saveTaskString();

}
