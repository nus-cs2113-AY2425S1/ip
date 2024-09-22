package nova.task;

import nova.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    LocalDate by;

    public Deadline (String description, LocalDate by) {
        super(description);
        this.by = by;
        printAcknowledgementMessage(getTaskInfo());
    }

    /**
     * Constructs a Deadline task with the specified status, description, and due date.
     * The task is marked as done if the status string is "X".
     *
     * @param isDone     The status of the task ("X" for done, otherwise not done).
     * @param description The description of the Deadline task.
     * @param by          The due date for the Deadline task.
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

    public boolean isDate(LocalDate date) {
        if (by.equals(date)) {
            return true;
        }
        return false;
    }
}
