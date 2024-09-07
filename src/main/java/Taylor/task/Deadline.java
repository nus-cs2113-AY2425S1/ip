package Taylor.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a task that must be completed by a specific date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description, completion status, and deadline.
     *
     * @param description The description of the Deadline task.
     * @param isCompleted The completion status of the task (true if completed, false otherwise).
     * @param by The deadline date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructs a Deadline task with the specified description and deadline.
     * The task is assumed to be not completed.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the Deadline task, which includes the task type,
     * description, and the deadline formatted as "MMM d yyyy".
     *
     * @return A string in the format "[D][ ] task description (by: MMM d yyyy)"
     *         where "[D]" indicates a Deadline task, "[ ]" or "[X]" indicates whether the task is completed,
     *         and the deadline is formatted as "MMM d yyyy".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string suitable for saving to a file, representing the Deadline task's data.
     *
     * @return A string in the format "D | 0 | task description | yyyy-MM-dd"
     *         where "D" indicates a Deadline task, "0" or "1" indicates whether the task is completed,
     *         and the task description and deadline date are stored.
     */
    @Override
    public String write() {
        return "D | " + (isCompleted ? "1" : "0") + " | " + description + " | " + by;
    }
}