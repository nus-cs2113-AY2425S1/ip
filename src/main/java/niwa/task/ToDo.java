package niwa.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a to-do task with a description and a completion status.
 * This class extends the niwa.task.Task class and specifies the type and short notation for to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do with the specified description.
     * The task is marked as undone by default. The type and short notation are set specifically for to-do tasks.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);  // Initialize the description in the superclass (niwa.task.Task)
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task as a string
     */
    @Override
    public String getType() {
        return "todo";
    }

    /**
     * Returns the short notation for the task type.
     *
     * @return The short type of the task as a string
     */
    @Override
    public String getShortType() {
        return "T";
    }

    public static Task parseTask(String inputString) {
        String format = "^T \\| (\\d+) \\| (.+?)$";
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(format);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(inputString);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Extract and trim the captured groups
            String segment1 = matcher.group(1).trim(); // isDone
            String segment2 = matcher.group(2).trim(); // Description

            // Return the segments as an array
            ToDo temp = new ToDo(segment2);
            if (segment1.equals("1")) {
                temp.markAsDone();
            }
            return temp;
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }
}
