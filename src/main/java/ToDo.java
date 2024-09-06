import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a to-do task with a description and a completion status.
 * This class extends the Task class and specifies the type and short notation for to-do tasks.
 */
public class ToDo extends Task {
    /** Regular expression format for parsing commands **/
    protected static String format = "todo (.+?)";

    /**
     * Constructs a to-do Task with the specified description.
     * The task is marked as undone by default. The type and short notation are set specifically for to-do tasks.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);  // Initialize the description in the superclass (Task)
        type = "todo";       // Set the task type to "todo"
        shortType = "T";     // Set the short notation for to-do tasks
    }

    /**
     * Parses the command string to extract event details.
     * The command should be in the format: "todo description".
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

            // Return the segments as an array
            return new String[]{segment1};
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }
}
