/**
 * Represents a task with a description, completion status, and associated time.
 * This is an abstract class that serves as a blueprint for specific types of tasks.
 */
public abstract class Task {
    protected String description; // Description of the task
    protected boolean isDone; // Completion status of the task
    protected String time; // New field for storing time

    /**
     * Constructs a Task with a given description and time.
     *
     * @param description The description of the task.
     * @param time       The time associated with the task.
     */
    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time; // Initialize the time
    }

    /**
     * Converts the task to a string format suitable for file storage.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Returns a string representation of the task for display.
     *
     * @return A string containing the task's completion status, description, and time.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description + (time != null ? " | " + time : "");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }
}
