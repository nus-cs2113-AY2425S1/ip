package CassHelpers.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task of type "Deadline", which includes a specific deadline.
 * It inherits from the Task class.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime deadline;

    /**
     * Constructs a new Deadline object with the specified task name and deadline time.
     *
     * @param taskName The name of the Deadline task.
     * @param deadline The deadline time.
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        setDeadline(deadline);
        setBy(this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm")));
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns a formatted string of the deadline time.
     *
     * @return The formatted deadline time.
     */
    public String getDeadlineString() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    /**
     * Overrides the default toString method to provide a specific format for Deadline tasks.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")";
    }

    /**
     * Returns the string format of the Deadline task to be written to a file.
     *
     * @return The writable string format of the Deadline task.
     */
    public String toWritableString() {
        return "D" + super.toWritableString() + "," + this.by;
    }
}
