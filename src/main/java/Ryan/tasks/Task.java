package Ryan.tasks;

/**
 * Represents a task with a description and a marked/unmarked status.
 */
public class Task {

    public static final String MARKED_SYMBOL = "[X]";
    public static final String UNMARKED_SYMBOL = "[ ]";
    public static final int MARKED_VALUE = 1;
    public static final int UNMARKED_VALUE = 0;
    public static final String DEFAULT_TASK_TYPE = "T";

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
        return String.format("%s | %d | %s", getTaskType(), isMarked() ? MARKED_VALUE : UNMARKED_VALUE, getDescription());
    }

    /**
     * Returns the type of the task. Default is "T" for Todo tasks.
     *
     * @return A string representing the task type.
     */
    public String getTaskType() {
        return DEFAULT_TASK_TYPE;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return (isMarked ? MARKED_SYMBOL : UNMARKED_SYMBOL) + " " + description;
    }
}
