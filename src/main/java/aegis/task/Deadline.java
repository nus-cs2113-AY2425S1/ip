package aegis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Deadline class represents a task with a deadline by which it must be completed.
 * It extends the Task class and includes an additional field for the deadline time.
 */
public class Deadline extends Task {

    protected Object by;

    /**
     * Constructs a Deadline task with the specified description and deadline time.
     * The deadline time can be either a LocalDateTime object or a string.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline time, either as a LocalDateTime or a string.
     */
    public Deadline(String description, Object by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task into a string format suitable for saving to a file.
     * The deadline time is formatted as a string based on its type.
     *
     * @return A string representing the Deadline task in a file-friendly format.
     */
    public String toFileFormat() {
        if (by instanceof LocalDateTime) {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                    ((LocalDateTime) by).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH));
        }
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type label, status,
     * description, and formatted deadline time.
     *
     * @return A string that represents the Deadline task, prefixed with "[D]" and showing the deadline.
     */
    @Override
    public String toString() {
        if (by instanceof LocalDateTime) {
            return "[D]" + super.toString() + " (by: " +
                    ((LocalDateTime) by).format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.ENGLISH)) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
