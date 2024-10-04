package Ryan.tasks;

/**
 * Represents a task with a description and a marked/unmarked status.
 */
public class Task {
    private final String description;
    private boolean isMarked = false;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Returns whether the task is marked as completed.
     *
     * @return true if the task is marked, false otherwise.
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task in a file-friendly format.
     *
     * @return A string formatted for file storage.
     */
    public String toFile() {
        return String.format("%s | %d | %s", getTaskType(), isMarked() ? 1 : 0, getDescription());
    }

    /**
     * Returns the type of the task. Default is "T" for Todo tasks.
     *
     * @return A string representing the task type.
     */
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[ ] ") + description;
    }
}
