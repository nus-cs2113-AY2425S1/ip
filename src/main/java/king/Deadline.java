package king;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. The task has a description and
 * a deadline for which it must be completed.
 */
public class Deadline extends Task {

    protected String taskEndTime;
    protected final String TASKTYPEICON = "[D]";
    private LocalDateTime deadlineDateTime;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description      The description of the task.
     * @param deadlineDateTime The date and time by which the task must be completed.
     */
    protected Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Returns the task description.
     */
    @Override
    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (by: " + taskEndTime + ")";
    }

    /**
     * Returns the deadline date and time for this task.
     */
    protected LocalDateTime getDeadlineDateTime() {
        return deadlineDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a MMM dd yyyy");
        return "[D]" + getStatusIcon() + description + " (by: " + deadlineDateTime.format(formatter) + ")";
    }
}
