package ryan.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    public static final String DEADLINE_TASK_TYPE = "D";
    public static final String DEADLINE_TASK_ICON = "[D]";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    protected LocalDate by;

    /**
     * Constructs a Deadline task with a description and a deadline.
     *
     * @param description The task's description.
     * @param by The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, INPUT_FORMATTER);
    }

    /**
     * Returns the task type, which is "D" for deadline tasks.
     *
     * @return A string representing the task type.
     */
    @Override
    public String getTaskType() {
        return DEADLINE_TASK_TYPE;
    }

    /**
     * Returns a string representation of the deadline task in a file-friendly format.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFile() {
        return String.format("%s | %d | %s | %s", getTaskType(), isMarked() ? MARKED_VALUE : UNMARKED_VALUE, getDescription(), by.toString());
    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return DEADLINE_TASK_ICON + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}
