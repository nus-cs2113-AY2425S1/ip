package niwa.task;

/**
 * Represents a task with a description and a completion status.
 * This is a base class for different types of tasks.
 */
public abstract class Task {
    /** Regular expression format for parsing commands **/
    protected static String format;
    /** Description of the task **/
    protected String description;
    /** Completion status of the task **/
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * The task is marked as undone by default.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        setDescription(description); // Set the description of the task
        markAsUndone();             // Mark the task as undone initially
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task as a string
     */
    public String getType() {
        return "";
    }

    /**
     * Returns the short notation for the task type.
     *
     * @return The short type of the task as a string
     */
    public String getShortType() {
        return "";
    }

    /**
     * Returns the status icon for the task.
     * The icon indicates whether the task is done or not.
     *
     * @return "[X]" if the task is done, "[ ]" if not
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // Return "X" for done, " " for not done
    }

    /**
     * Returns the status number for the task.
     * The number indicates whether the task is done or not.
     *
     * @return 1 if the task is done, 0 if not
     */
    public int getStatusNumber() {
        return isDone ? 1 : 0; // Return "X" for done, " " for not done
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
     * Returns the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the full information about the task in a formatted string.
     * The format is: "[shortType][statusIcon] description".
     *
     * @return A formatted string containing the task's full information
     */
    public String getFullInfo() {
        return String.format("[%s][%s] %s", getShortType(), getStatusIcon(), getDescription());
    }

    /**
     * Returns the full information about the task in a formatted string for file output.
     * The format is: "shortType | isDone | description".
     *
     * @return A formatted string containing the task's full information
     */
    public String getFileOutput() {
        return String.format("%s | %s | %s", getShortType(), getStatusNumber(), getDescription());
    }

    /**
     * Return a task by reading a formatted String.
     *
     * @param inputString The input information of the task.
     */
    public static Task parseTask(String inputString){
        return null;
    }
}
