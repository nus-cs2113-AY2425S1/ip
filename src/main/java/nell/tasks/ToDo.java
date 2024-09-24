package nell.tasks;

import java.time.LocalDate;

/**
 * Represents a ToDo task in the task list.
 */
public class ToDo extends Task {
    public static final String TASK_TYPE = "T";

    /**
     * Constructs a ToDo with the given description
     *
     * @param description The given description
     */
    public ToDo(String description) {
        super(description, TASK_TYPE);
    }

    /**
     * Constructs a ToDo with the given data
     *
     * @param description The given description
     * @param isDone Whether the task is done or not
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.type = TASK_TYPE;
    }

    public String toString() {
        return super.toString();
    }

    /**
     * Returns true if the given task occurs on a given date.
     * Always returns false as ToDo tasks are undated.
     *
     * @param date The specified date
     * @return False in all cases
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }
}
