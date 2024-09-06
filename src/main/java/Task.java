import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task with a description and a completion status.
 * This is a base class for different types of tasks.
 */
public class Task {
    /** Regular expression format for parsing commands **/
    protected static String format;
    /** Task type (e.g., "todo", "event", "deadline") **/
    protected String type = " ";
    /** Short notation for the task type (e.g., "T" for todo, "E" for event) **/
    protected String shortType = " ";
    /** Description of the task **/
    protected String description;
    /** Completion status of the task **/
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
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
        return type;
    }

    /**
     * Returns the short notation for the task type.
     *
     * @return The short type of the task as a string
     */
    public String getShortType() {
        return shortType;
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
     * Parses the command string to extract event details.
     * The command should be in the format: "description /from startDay /to endDay".
     *
     * @param command The command string to parse
     * @return An array containing the description, startDay, and endDay, or null if the command format is invalid
     */
    public static String[] getArgument(String command) {
        return null;
    }
}
