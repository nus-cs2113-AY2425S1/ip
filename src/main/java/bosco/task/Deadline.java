package bosco.task;

import bosco.parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents a Deadline, a subclass of Task.
 * A <code>Deadline</code> object also contains a due time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Class constructor.
     *
     * @param description Description of this Deadline.
     * @param isDone Whether this Deadline is done.
     * @param by Time this Deadline is due.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets the time this Deadline is due.
     *
     * @return String of when this Deadline is due.
     */
    public String getBy() {
        return DateTimeParser.formatDateTime(by);
    }

    /**
     * Overrides the <code>toString</code> method of Task
     * to format the string representation of this Deadline.
     *
     * @return Formatted string of this Deadline,
     * with Deadline indicator and due time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
