import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type "Deadline".
 * A Deadline task has a description and a specific time by which it must be completed.
 */
public class Deadline extends Task {

    public LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task, including its type, status, and due date.
     *
     * @return A string in the format "[D][status] description (by: due date)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}