package model;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description and status.
     *
     * @param description the description of the todo task
     * @param isDone     the status of the task, true if done, false otherwise
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[T][" + (isDone() ? "X" : " ") + "] " + getDescription();
    }

    /**
     * Returns the save format of the Todo task for persistence.
     *
     * @return the save format string
     */
    public String saveFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
