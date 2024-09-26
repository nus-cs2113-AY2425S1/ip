package nova.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that extends the base Task class.
 * A Deadline task has a description, a completion status, and a due date.
 */
public class Deadline extends Task{

    public static final String TYPE = "D";
    LocalDate by;

    /**
     * Constructs a Deadline task with the description and due date.
     *
     * @param description The description of the Deadline task.
     * @param by The due date for the Deadline task.
     */
    public Deadline (String description, LocalDate by) {
        super(description);
        this.by = by;
        printAcknowledgementMessage(getTaskInfo());
    }

    /**
     * Constructs a Deadline task with the specified status, description, and due date.
     * The task is marked as done if the status string is "X".
     * This function is used to load Deadline task from storage
     *
     * @param isDone The status of the task ("X" for done, otherwise not done).
     * @param description The description of the Deadline task.
     * @param by The due date for the Deadline task.
     */
    public Deadline(String isDone, String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its status icon, description, and due date.
     *
     * @return The Deadline task information as a string.
     */
    @Override
    public String getTaskInfo() {
        return "[D][" + this.getStatusIcon() + "] " + description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for storage purposes.
     *
     * @return A string representing the Deadline task for storage.
     */
    @Override
    public String getTaskStorageInfo() {
        return "D" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + by;
    }

    /**
     * Checks if the deadline is associated with a given date.
     * This method compares the task's deadline date to the input date.
     *
     * @param date The date to check against.
     * @return true if the task's deadline matches the input date, false otherwise.
     */
    @Override
    public boolean isDate(LocalDate date) {
        if (by.equals(date)) {
            return true;
        }
        return false;
    }
}
