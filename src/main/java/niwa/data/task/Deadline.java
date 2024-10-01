package niwa.data.task;

import niwa.exception.NiwaException;
import niwa.utils.NiwaUtils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code Deadline} represents a deadline task with a description, a due date and a completion status.
 * This class extends the {@code Task} class.
 */
public class Deadline extends Task {
    /** The due date of the deadline task **/
    protected LocalDateTime byDay;

    /**
     * Constructs a deadline with the specified description and due date.
     * The task is marked as undone by default.
     *
     * @param description The description of the deadline task
     * @param byDay The due date for the deadline task
     */
    public Deadline(String description, String byDay) throws NiwaException {
        super(description);  // Initialize the description in the superclass (Task)
        this.byDay = NiwaUtils.parseDateTime(byDay);  // Set the due date for the deadline task
    }


    /**
     * Returns the type of the task.
     *
     * @return The type of the task as a string
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Returns the short notation for the task type.
     *
     * @return The short type of the task as a string
     */
    @Override
    public String getShortType() {
        return "D";
    }


    /**
     * Getter for the due date of the deadline task
     */
    public LocalDateTime getByDay() {
        return byDay;
    }

    /**
     * Returns the full information about the task in a formatted string.
     * The format: "[shortType][statusIcon] description (by: byDay)".
     *
     * @return A formatted string containing the task's full information.
     */
    @Override
    public String getFullInfo() {
        return String.format("[%s][%s] %s (by: %s)",
                getShortType(), getStatusIcon(), getDescription(),
                NiwaUtils.getDateTimeString(getByDay()));
    }

    /**
     * Returns the full information about the task in a formatted string for file output.
     * The format: "shortType | isDone | description | byDay".
     *
     * @return A formatted string containing the task's full information for file output.
     */
    @Override
    public String getFileOutput() {
        return String.format("%s | %s | %s | %s",
                getShortType(), getStatusNumber(), getDescription(),
                NiwaUtils.getDateTimeSaveString(getByDay()));
    }

    /**
     * Parses a task from a formatted string.
     *
     * @param inputString The formatted string representing the task.
     * @return A {@code Deadline} object if parsing is successful; {@code null} otherwise.
     */
    public static Task parseTask(String inputString) {
        String format = "^D \\| (\\d+) \\| (.+?) \\| (.+?)$";
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(format);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(inputString);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Extract and trim the captured groups
            String segment1 = matcher.group(1).trim(); // isDone
            String segment2 = matcher.group(2).trim(); // Description
            String segment3 = matcher.group(3).trim(); // By day

            // Create a new Deadline
            Deadline temp = null;
            try {
                temp = new Deadline(segment2, segment3);
            } catch (NiwaException e) {
                return null;
            }

            // Mark as done if the first segment = 1 (done)
            if (segment1.equals("1")) {
                temp.markAsDone();
            }
            return temp;
        } else {
            // Return null if the command does not match the expected format
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
            return true;
        } else if (inputTask != null) {
            return inputTask.description.equals(description)
                    && ((Deadline) inputTask).byDay.equals(byDay);
        } else {
            return false;
        }
    }
}
