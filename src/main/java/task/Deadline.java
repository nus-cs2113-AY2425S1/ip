package task;

import java.time.LocalDateTime;
import main.List;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    protected LocalDateTime byDate;

    /**
     * Constructs a Deadline with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param byDate The due date of the deadline task.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a string representation of the deadline task, including its completion status
     * and description with the due date.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return ("[D][" + getDoneStatusIcon() + "] " + description + " (by: " + List.convertDeadlineDateAsString(byDate) + ")");
    }

    /**
     * Returns a formatted string representation of the deadline task for storage.
     *
     * @return A formatted string representing the deadline task suitable for saving to a file.
     */
    @Override
    public String formattedTask() {
        return ("D | " + getDoneStatusIcon() + " | " + description + " | " + List.convertDeadlineDateAsString(byDate));
    }

}
