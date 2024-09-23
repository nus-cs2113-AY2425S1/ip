package niwa.data.task;

import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaExceptionMessages;
import niwa.utils.NiwaUtils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an event task with a description, a start day, and an end day.
 * This class extends the niwa.data.task.Task class and specifies the type and short notation for event tasks.
 */
public class Event extends Task {
    /** niwa.data.task.Event information **/
    protected LocalDateTime fromDay; // The start day of the event
    protected LocalDateTime toDay;   // The end day of the event

    /**
     * Constructs an event with the specified description, start day, and end day.
     * The task is marked as undone by default. The type and short notation are set specifically for event tasks.
     *
     * @param description The description of the event
     * @param fromDay The day when the event starts
     * @param toDay The day when the event ends
     */
    public Event(String description, String fromDay, String toDay) throws NiwaException {
        super(description);  // Initialize the description in the superclass (niwa.data.task.Task)
        this.fromDay = NiwaUtils.parseDateTime(fromDay); // Set the start day of the event
        this.toDay = NiwaUtils.parseDateTime(toDay);     // Set the end day of the event

        if (this.fromDay.isAfter(this.toDay)) {
            String message = String.format(NiwaExceptionMessages.MESSAGE_INVALID_DATE_PERIOD, fromDay, toDay);
            throw new NiwaException(message);
        }
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task as a string
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Returns the short notation for the task type.
     *
     * @return The short type of the task as a string
     */
    @Override
    public String getShortType() {
        return "E";
    }

    // Getter for the start day of the event
    public LocalDateTime getFromDay() {
        return fromDay;
    }

    // Getter for the end day of the event
    public LocalDateTime getToDay() {
        return toDay;
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
                getShortType(), getStatusIcon(), getDescription(),
                NiwaUtils.getDateTimeString(getFromDay()),
                NiwaUtils.getDateTimeString(getToDay()));
    }

    /**
     * Returns the full information about the task in a formatted string for file output.
     * The format is: "shortType | isDone | description | [fromDay] * [toDay]".
     *
     * @return A formatted string containing the task's full information
     */
    @Override
    public String getFileOutput() {
        return String.format("%s | %s | %s | %s -> %s",
                getShortType(), getStatusNumber(), getDescription(),
                NiwaUtils.getDateTimeSaveString(getFromDay()),
                NiwaUtils.getDateTimeSaveString(getToDay()));
    }

    public static Task parseTask(String inputString) {
        String format = "^E \\| (\\d+) \\| (.+?) \\| (.+?) -> (.+?)$";
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(format);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(inputString);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Extract and trim the captured groups
            String segment1 = matcher.group(1).trim(); // isDone
            String segment2 = matcher.group(2).trim(); // Description
            String segment3 = matcher.group(3).trim(); // From day
            String segment4 = matcher.group(4).trim(); // To day
            // Return the segments as an array
            Event temp = null;
            try {
                temp = new Event(segment2, segment3, segment4);
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
                    && ((Event) inputTask).fromDay.equals(fromDay)
                    && (((Event) inputTask).toDay.equals(toDay));
        } else {
            return false;
        }
    }
}
