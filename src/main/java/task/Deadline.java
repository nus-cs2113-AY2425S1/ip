package task;


import java.time.LocalDateTime;
import main.TaskList;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its completion status
     * and description with the due date.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return ("[D][" + getDoneStatusIcon() + "] " + description + " (by: " + TaskList.convertDeadlineDateAsString(by) + ")");
    }

    /**
     * Returns a formatted string representation of the deadline task for storage.
     *
     * @return A formatted string representing the deadline task suitable for saving to a file.
     */
    @Override
    public String formattedTask() {
        return ("D | " + getDoneStatusIcon() + " | " + description + " | " + TaskList.convertDeadlineDateAsString(by));
    }

}
