package niwa.data.task;

import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.utils.NiwaUtils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a deadline task with a description and a due date.
 * This class extends the niwa.data.task.Task class and specifies the type and short notation for deadline tasks.
 */
public class Deadline extends Task {
    /** niwa.data.task.Deadline information **/
    protected LocalDateTime byDay; // The due date of the deadline task

    /**
     * Constructs a deadline with the specified description and due date.
     * The task is marked as undone by default. The type and short notation are set specifically for deadline tasks.
     *
     * @param description The description of the deadline task
     * @param byDay The due date for the deadline task
     */
    public Deadline(String description, String byDay) throws NiwaException {
        super(description);  // Initialize the description in the superclass (niwa.data.task.Task)
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


    // Getter for the due date of the deadline task
    public LocalDateTime getByDay() {
        return byDay;
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
                getShortType(), getStatusIcon(), getDescription(),
                NiwaUtils.getDateTimeString(getByDay()));
    }

    /**
     * Returns the full information about the task in a formatted string for file output.
     * The format is: "shortType | isDone | description | [byDay]".
     *
     * @return A formatted string containing the task's full information
     */
    @Override
    public String getFileOutput() {
        return String.format("%s | %s | %s | %s",
                getShortType(), getStatusNumber(), getDescription(),
                NiwaUtils.getDateTimeSaveString(getByDay()));
    }

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

            // Return the segments as an array
            Deadline temp = null;
            try {
                temp = new Deadline(segment2, segment3);
            } catch (NiwaException e) {
                return null;
            }

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
