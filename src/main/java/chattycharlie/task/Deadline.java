package chattycharlie.task;

import chattycharlie.commands.CommandType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, a task with a specified deadline.
 * A <code>Deadline</code> task has a description and a date by which it must be completed.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a <code>Deadline</code> task with the specified description and due date.
     *
     * @param description the description of the deadline task.
     * @param by the due date for the task in <code>yyyy-MM-dd</code> format.
     */
    public Deadline(String description, String by) {
        super(description, CommandType.DEADLINE);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets the due date of the deadline task.
     *
     * @return the due date as a <code>LocalDate</code>.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task, including its marked status,
     * description, and due date.
     *
     * @return a string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for saving.
     *
     * @return a string representation of the deadline task formatted for saving.
     */
    @Override
    public String toSaveFormat() {
        return "[D]" + super.toSaveFormat() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}