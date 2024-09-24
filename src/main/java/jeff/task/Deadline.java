package jeff.task;

import jeff.exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the task management system.
 * The <code>Deadline</code> class extends the <code>Task</code> class to provide
 * specific functionalities related to tasks that have a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) throws InvalidFormatException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Deadline date input is not formatted correctly...");
        }
    }

    /**
     * Returns a string representation of the deadline task's content for file storage.
     * This includes a prefix "D" followed by the task's status number, description,
     * and the deadline.
     *
     * @return A string formatted for file storage, indicating the task type as "D".
     */
    @Override
    public String fileContent() {
        return "D" + super.fileContent() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a string representation of the deadline task.
     * This includes a prefix "[D]" to indicate the task type as a deadline task,
     * followed by the task's status icon, description, and deadline.
     *
     * @return A string that represents the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}