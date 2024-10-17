package niwa.data.task;

import niwa.exception.NiwaException;
import niwa.messages.NiwaExceptionMessages;
import niwa.utils.NiwaUtils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code Event} represents an event task with a description, a start day, and an end day.
 * This class extends the {@code Task} class.
 */
public class Event extends Task {
    /** The start day of the event */
    protected LocalDateTime fromDay;
    /** The end day of the event */
    protected LocalDateTime toDay;

    /**
     * Constructs an event with the specified description, start day and end day.
     * The task is marked as undone by default.
     *
     * @param description The description of the event.
     * @param fromDay The day when the event starts.
     * @param toDay The day when the event ends.
     * @throws NiwaException If the end day is before the start day.
     */
    public Event(String description, String fromDay, String toDay) throws NiwaException {
        super(description);  // Initialize the description in the superclass (Task)
        this.fromDay = NiwaUtils.parseDateTime(fromDay); // Set the start day of the event
        this.toDay = NiwaUtils.parseDateTime(toDay);     // Set the end day of the event

        // Check if the start day is after the end day
        if (this.fromDay.isAfter(this.toDay)) {
            String message = String.format(NiwaExceptionMessages.MESSAGE_INVALID_DATE_PERIOD, fromDay, toDay);
            throw new NiwaException(message); // Throw an exception if the date period is invalid
        }
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task as a string.
     */
    @Override
    public String getType() {
        return "event"; // Return the specific type for event tasks
    }

    /**
     * Returns the short notation for the task type.
     *
     * @return The short type of the task as a string.
     */
    @Override
    public String getShortType() {
        return "E"; // Return the specific short notation for event tasks
    }

    /**
     * Getter for the start day of the event.
     *
     * @return The start day of the event as a {@code LocalDateTime}.
     */
    public LocalDateTime getFromDay() {
        return fromDay;
    }

    /**
     * Getter for the end day of the event.
     *
     * @return The end day of the event as a {@code LocalDateTime}.
     */
    public LocalDateTime getToDay() {
        return toDay;
    }

    /**
     * Returns the full information about the task in a formatted string.
     * The format: "[shortType][statusIcon] description (from: fromDay to: toDay)".
     *
     * @return A formatted string containing the event's full information.
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
     * The format: "shortType | isDone | description | fromDay -> toDay".
     *
     * @return A formatted string containing the task's full information for file output.
     */
    @Override
    public String getFileOutput() {
        return String.format("%s | %s | %s | %s -> %s",
                getShortType(), getStatusNumber(), getDescription(),
                NiwaUtils.getDateTimeSaveString(getFromDay()),
                NiwaUtils.getDateTimeSaveString(getToDay()));
    }

    /**
     * Parses a task from a formatted string.
     *
     * @param inputString The formatted string representing the task.
     * @return A {@code Event} if parsing is successful; {@code null} otherwise.
     */
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

            // Create a new Event
            Event temp = null;
            try {
                temp = new Event(segment2, segment3, segment4);
            } catch (NiwaException e) {
                return null; // Return null if there's an exception while creating the Event
            }

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
            return inputTask.description.equals(description)
                    && ((Event) inputTask).fromDay.equals(fromDay)
                    && (((Event) inputTask).toDay.equals(toDay)); // Compare descriptions and date ranges
        } else {
            return false;
        }
    }
}
