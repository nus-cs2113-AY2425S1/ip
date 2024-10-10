package november.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a task that must be completed by a specific date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description Describes the task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Prints the confirmation message for an added Deadline task.
     */
    @Override
    public void printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yy");
        String formattedByDateTime = by.format(formatter);
        System.out.print("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
        System.out.println(" (by: " + formattedByDateTime + ")");
    }

    /**
     * Returns the task icon specific to Deadline tasks.
     *
     * @return The string "D" representing a Deadline task.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline task for saving to a file.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "deadline | " + isComplete + " | " + description + " | " + by;
    }
}
