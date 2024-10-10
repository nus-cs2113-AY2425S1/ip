package chattycharlie.task;

import chattycharlie.CharlieExceptions;
import chattycharlie.commands.CommandType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, a task with a specified deadline.
 * A <code>Deadline</code> task has a description and a date by which it must be completed.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Constructs a <code>Deadline</code> task with the specified description and due date.
     *
     * @param description the description of the deadline task.
     * @param by the due date for the task in <code>yyyy-MM-dd</code> format.
     */
    public Deadline(String description, String by) throws CharlieExceptions {
        super(description, CommandType.DEADLINE);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            String[] byParts = by.split(" ");

            if (byParts.length != 2) {
                throw CharlieExceptions.invalidFormat("deadline DESCRIPTION by DATE TIME"); // Validation for format
            }

            if (!byParts[1].matches("\\d{2}:\\d{2}")) {
                throw CharlieExceptions.invalidFormat("Time format should be HH:mm (e.g., 14:00)");
            }

            this.byDate = LocalDate.parse(byParts[0], dateFormatter);
            this.byTime = LocalTime.parse(byParts[1], timeFormatter);
        } catch (CharlieExceptions e) {
            throw CharlieExceptions.invalidFormat("Invalid deadline format. Expected format: 'yyyy-MM-dd HH:mm'");
        }
    }

    /**
     * Gets the due date of the deadline task.
     *
     * @return the due date as a <code>LocalDate</code>.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns a string representation of the deadline task, including its marked status,
     * description, and due date.
     *
     * @return a string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + byTime + ")";
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for saving.
     *
     * @return a string representation of the deadline task formatted for saving.
     */
    @Override
    public String toSaveFormat() {
        return "[D]" + super.toSaveFormat() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " " + byTime + ")";
    }
}
