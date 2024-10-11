package task;

/**
 * Represents a generic task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the icon representing the task's completion status.
     *
     * @return "X" if the task is done, otherwise a space character
     */
    public String getIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
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
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string format of the task for saving to a file.
     * The format is: status | description
     *
     * @return a string for file storage
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the task,
     * including its completion status and description.
     *
     * @return a string in the format [status] description
     */
    @Override
    public String toString() {
        return "[" + getIcon() + "]" + description;
    }
}
