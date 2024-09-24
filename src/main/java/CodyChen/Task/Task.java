package CodyChen.Task;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or not done, retrieve its description,
 * and get its status icon for display purposes.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initialized as not done.
     *
     * @param description The description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */

    public void markDel() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's status.
     * The status is represented as "[X]" if the task is done,
     * or "[ ]" if it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] "; // mark done task with X
    }

    /**
     * Returns the type of the task.
     * This implementation returns 'u' for a generic task.
     *
     * @return The character representing the type of the task.
     */
    public char getType() {
        return 'u';
    }

    public String getTo() {
        return "";
    }

    public String formattedDeadline(){
        return "";
    }

    public String formattedEvent(){
        return "";
    }
}
