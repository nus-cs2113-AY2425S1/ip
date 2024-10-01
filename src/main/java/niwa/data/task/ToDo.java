package niwa.data.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code ToDo} represents a to-do task with a description and a completion status.
 * This class extends the {@code Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do with the specified description.
     * The task is marked as undone by default.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description); // Initialize the description in the superclass (Task)
    }

    /**
     * Getter for the type of the task.
     *
     * @return The type of the task as a string.
     */
    @Override
    public String getType() {
        return "todo";
    }

    /**
     * Getter for the short notation for the task type.
     *
     * @return The short type of the task as a string.
     */
    @Override
    public String getShortType() {
        return "T";
    }

    /**
     * Parses a task from a formatted string.
     *
     * @param inputString The formatted string representing the task.
     * @return A {@code ToDo} object if parsing is successful; {@code null} otherwise.
     */
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

            // Create a new ToDo
            ToDo temp = new ToDo(segment2);
            // Mark as done if the first segment = 1 (done)
            if (segment1.equals("1")) {
                temp.markAsDone();
            }
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Returns true if both tasks have the same identity fields.
     *
     * @param inputTask The task to compare.
     * @return true if both tasks are considered the same; false otherwise.
     */
    @Override
    public boolean isSameTask(Task inputTask) {
        if (inputTask == this) {
            return true; // Check if both references point to the same object
        } else if (inputTask != null) {
            return inputTask.description.equals(description); // Compare descriptions
        } else {
            return false;
        }
    }
}
