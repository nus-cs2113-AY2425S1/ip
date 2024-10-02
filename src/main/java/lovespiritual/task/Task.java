package lovespiritual.task;

/**
 * Represents a task with a description and a marked/unmarked status.
 */
public class Task {
    /** Description of the task. */
    public String description;
    /** Status of the task, whether it is marked (completed) or not marked (not completed). */
    public boolean isMarked;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially unmarked.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the string representation of this task, including its marked/unmarked status.
     *
     * @return A string in the format "[status] description".
     *         Status is "[X]" if marked and "[ ]" if unmarked.
     */
    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[ ] ") + description;
    }
}
