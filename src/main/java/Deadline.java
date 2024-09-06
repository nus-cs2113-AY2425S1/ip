import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a deadline task with a description and a due date.
 * This class extends the Task class and specifies the type and short notation for deadline tasks.
 */
public class Deadline extends Task {

    /** Regular expression format for parsing commands **/
    protected static String format = "deadline (.+?)\\s*/by\\s*(.+?)";

    /** Deadline information **/
    protected String byDay; // The due date of the deadline task

    /**
     * Constructs a deadline Task with the specified description and due date.
     * The task is marked as undone by default. The type and short notation are set specifically for deadline tasks.
     *
     * @param description The description of the deadline task
     * @param byDay The due date for the deadline task
     */
    public Deadline(String description, String byDay) {
        super(description);  // Initialize the description in the superclass (Task)
        this.byDay = byDay;  // Set the due date for the deadline task
        type = "deadline";   // Set the task type to "deadline"
        shortType = "D";     // Set the short notation for deadline tasks
    }

    /**
     * Parses the command string to extract deadline details.
     * The command should be in the format: "deadline description /by dueDate".
     *
     * @param command The command string to parse
     * @return An array containing the description and dueDate, or null if the command format is invalid
     */
    public static String[] getArgument(String command) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(format);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(command);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Extract and trim the captured groups
            String segment1 = matcher.group(1).trim(); // Description
            String segment2 = matcher.group(2).trim(); // Due date

            // Return the segments as an array
            return new String[]{segment1, segment2};
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    // Getter for the due date of the deadline task
    public String getByDay() {
        return byDay;
    }

    // Setter for the due date of the deadline task
    public void setByDay(String byDay) {
        this.byDay = byDay;
    }

    /**
     * Returns a string containing the full information about the deadline in a formatted manner.
     * The format is: "[shortType][statusIcon] description (by: byDay)".
     *
     * @return A formatted string containing the deadline's full information
     */
    @Override
    public String getFullInfo() {
        return String.format("[%s][%s] %s (by: %s)",
                getShortType(), getStatusIcon(), getDescription(), getByDay());
    }
}
