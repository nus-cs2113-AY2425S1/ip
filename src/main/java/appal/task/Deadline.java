package appal.task;

import appal.exception.AppalException;
import appal.exception.InvalidDeadlineFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected static final String command = "deadline";
    protected LocalDate by;

    /**
     * Class Constructor.
     *
     * @param description Description of Deadline Task.
     * @param by Deadline date in yyyy-mm-dd format.
     * @throws AppalException if the user inputs an invalid date format for the specified by parameter.
     */
    public Deadline(String description, String by) throws AppalException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    /**
     * Returns the deadline date in LocalDate format yyyy-mm-dd.
     *
     * @return Deadline date in LocalDate type.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string of the deadline date in the format MMM d yyyy.
     *
     * @return String representation of the deadline date in the format MMM d yyyy.
     */
    public String getFormattedDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of a Deadline task printed in the task list,
     * with "[D]" indicating a Deadline task, indication of completion and Deadline details.
     *
     * @return String representation Deadline Task for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getFormattedDate() + ")";
    }

    /**
     * Returns String representation of a Deadline Task when stored and saved in a text file,
     * including command type, task description and additional information based of type of task.
     *
     * @return String representation of a Deadline Task for saving.
     */
    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo() + ", " + by;
    }
}
