import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date and time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date/time for the task as a LocalDateTime.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its description and due date/time.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }

    /**
     * Returns a string representation of the Deadline task, formatted for saving to a file.
     * The due date/time is formatted as 'yyyy-MM-dd HHmm' to preserve precision.
     *
     * @return A string representation of the Deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + super.toFileFormat() + " | " + by.format(formatter);
    }

    /**
     * Formats the due date to a readable format (e.g., Dec 2 2019, 6:00 PM).
     *
     * @return The formatted due date string.
     */
    public String getFormattedBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return by.format(formatter);
    }
}
