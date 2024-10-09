package bitwise.tasks;

import bitwise.constants.Icons;

/**
 * The {@code Deadline} class represents a task that has a specific deadline.
 * This class extends the {@code Task} class and includes additional information about the deadline of the task.
 */
public class Deadline extends Task {

    protected String deadline;
    protected static final String symbol = Icons.ICON_DEADLINE;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description a {@code String} that describes the task
     * @param deadline a {@code String} representing the deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the {@code Deadline} task, including its symbol,
     * description, and deadline.
     *
     * @return a {@code String} that represents the {@code Deadline} task
     */
    @Override
    public String toString() {
        return symbol + " " + super.toString() + "(by: " + deadline + ")";
    }
}
