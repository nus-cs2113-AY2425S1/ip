package niwa.data.task;

/**
 * The class {@code Task} represents a task with a description and a completion status.
 * This is a base class for different types of tasks.
 */
public abstract class Task {
    /** Regular expression format for parsing commands */
    protected static String format;
    /** Description of the task */
    protected String description;
    /** Completion status of the task */
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * The task is marked as undone by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        setDescription(description); // Set the description of the task
        markAsUndone();             // Mark the task as undone initially
    }

    /**
     * Getter for the type of the task.
     *
     * @return The type of the task as a string.
     */
    public String getType() {
        return ""; // Default implementation, should be overridden by subclasses
    }

    /**
     * Getter for the short notation for the task type.
     *
     * @return The short type of the task as a string.
     */
    public String getShortType() {
        return ""; // Default implementation, should be overridden by subclasses
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "[X]" if the task is done, "[ ]" if not.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // Return "X" for done, " " for not done
    }

    /**
     * Returns the status number for the task.
     *
     * @return 1 if the task is done, 0 if not.
     */
    public int getStatusNumber() {
        return isDone ? 1 : 0; // Return 1 for done, 0 for not done
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
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Getter of the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the full information about the task in a formatted string.
     * The format: "[shortType][statusIcon] description".
     *
     * @return A formatted string containing the task's full information.
     */
    public String getFullInfo() {
        return String.format("[%s][%s] %s", getShortType(), getStatusIcon(), getDescription());
    }

    /**
     * Returns the full information about the task in a formatted string for file output.
     * The format: "shortType | isDone | description".
     *
     * @return A formatted string containing the task's full information for file output.
     */
    public String getFileOutput() {
        return String.format("%s | %s | %s", getShortType(), getStatusNumber(), getDescription());
    }

    /**
     * Returns a task by reading a formatted String.
     *
     * @param inputString The input information of the task.
     * @return A {@code Task} if parsing is successful; {@code null} otherwise.
     */
    public static Task parseTask(String inputString){
        return null; // Default implementation, should be overridden by subclasses
    }

    /**
     * Returns true if both tasks have the same identity fields.
     *
     * @param inputTask The task to compare.
     * @return true if both tasks are considered the same; false otherwise.
     */
    public abstract boolean isSameTask(Task inputTask);
}
