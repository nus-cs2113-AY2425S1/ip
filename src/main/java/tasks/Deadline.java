package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static constants.Regex.DISPLAY_DATE_FORMAT;
import static constants.Regex.INPUT_DATE_FORMAT;

/**
 * Represents a task with a deadline in Bento.
 * A Deadline has a name and a due date represented by a {@link LocalDate}.
 */
public class Deadline extends Task {
    private LocalDate by;
    private final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);
    private final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    /**
     * Constructs a Deadline with the specified task name and due date.
     *
     * @param taskName The name of the Deadline.
     * @param by       The due date for the Deadline.
     */
    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the due date of the Deadline in a human-readable format.
     *
     * @return The due date formatted for display.
     */
    public String getByAsString() {
        return displayFormatter.format(by);
    }

    /**
     * Returns the due date in a command-friendly format, typically used for saving.
     *
     * @return The due date formatted for saving as a command.
     */
    public String getByAsCommand() {
        return saveFormatter.format(by);
    }

    /**
     * Returns the due date of the Deadline as a {@link LocalDate}.
     *
     * @return The due date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Sets the due date for the Deadline.
     *
     * @param by The new due date for the Deadline.
     */
    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline.
     * The format includes the Deadline type indicator "[D]" followed by the Deadline's done marker,
     * Deadline name, and the due date in a human-readable format.
     *
     * @return A string representing the Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getByAsString());
    }

    /**
     * Returns the command string to recreate the Deadline.
     * This is used for saving the Deadline in a format that can be reloaded later.
     *
     * @return The task as a command string.
     */
    @Override
    public String getTaskAsCommand() {
        return String.format("deadline %s /by %s", getTaskName(), getByAsCommand());
    }
}
