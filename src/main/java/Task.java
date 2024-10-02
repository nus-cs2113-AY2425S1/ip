/**
 * Represents a task in the task manager.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, showing whether it is done or not.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
       return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns a string representation of the task, showing whether it is done or not in file format.
     *
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
}
