import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an event task with a description, a start day, and an end day.
 * This class extends the Task class and specifies the type and short notation for event tasks.
 */
public class Event extends Task {

    /** Regular expression format for parsing event commands **/
    protected static String format = "(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*)";

    /** Event information **/
    protected String fromDay; // The start day of the event
    protected String toDay;   // The end day of the event

    /**
     * Constructs an event Task with the specified description, start day, and end day.
     * The task is marked as undone by default. The type and short notation are set specifically for event tasks.
     *
     * @param description The description of the event
     * @param fromDay The day when the event starts
     * @param toDay The day when the event ends
     */
    public Event(String description, String fromDay, String toDay) {
        super(description);  // Initialize the description in the superclass (Task)
        this.fromDay = fromDay; // Set the start day of the event
        this.toDay = toDay;     // Set the end day of the event
        type = "event";        // Set the task type to "event"
        shortType = "E";       // Set the short notation for event tasks
    }

    /**
     * Parses the command string to extract event details.
     * The command should be in the format: "description /from startDay /to endDay".
     *
     * @param command The command string to parse
     * @return An array containing the description, startDay, and endDay, or null if the command format is invalid
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
            String segment2 = matcher.group(2).trim(); // Start day
            String segment3 = matcher.group(3).trim(); // End day

            // Return the segments as an array
            return new String[]{segment1, segment2, segment3};
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    // Getter for the start day of the event
    public String getFromDay() {
        return fromDay;
    }

    // Setter for the start day of the event
    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }

    // Getter for the end day of the event
    public String getToDay() {
        return toDay;
    }

    // Setter for the end day of the event
    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    /**
     * Returns a string containing the full information about the event in a formatted manner.
     * The format is: "[shortType][statusIcon] description (from: fromDay to: toDay)".
     *
     * @return A formatted string containing the event's full information
     */
    @Override
    public String getFullInfo() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                getShortType(), getStatusIcon(), getDescription(), getFromDay(), getToDay());
    }
}
