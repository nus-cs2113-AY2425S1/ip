package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

/**
 * Represents a task that has a deadline.
 * The task includes a description and a deadline date, which is specified after "/by".
 */
public class Deadline extends Task {
    protected final String deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param input The task description followed by the deadline in the format "description /by date".
     * @throws EmptyArgumentException If the task description is empty or blank.
     * @throws InvalidCommandFormatException If the command format is invalid (i.e., if the deadline date is not properly formatted after "/by").
     */
    public Deadline(String input) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 2)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Deadline description");
        }

        String[] parts = input.split("/", 2);

        if (parts.length != 2 || parts[1].isBlank()) {
            throw new InvalidCommandFormatException("Invalid command format, " +
                    "Deadline dates should come after \"/by \"");
        }

        String datePart = parts[1].trim();
        if (!datePart.startsWith("by ")) {
            throw new InvalidCommandFormatException("Deadline dates should start with \"/by \"");
        }

        this.deadline = datePart.substring(3); // ignore "by "
        this.icon = "D";
    }

    /**
     * Constructs a new {@code Deadline} task with the specified description, deadline, and completion status.
     *
     * @param input The task description followed by the deadline in the format "description /by date".
     * @param isDone Whether the task is marked as completed.
     * @throws EmptyArgumentException If the task description is empty or blank.
     * @throws InvalidCommandFormatException If the command format is invalid (i.e., if the deadline date is not properly formatted after "/by").
     */
    public Deadline(String input, boolean isDone) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 2)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Deadline description");
        }

        String[] parts = input.split("/", 2);

        if (parts.length != 2 || parts[1].isBlank()) {
            throw new InvalidCommandFormatException("Invalid command format, " +
                    "Deadline dates should come after \"/by \"");
        }

        String datePart = parts[1].trim();
        if (!datePart.startsWith("by ")) {
            throw new InvalidCommandFormatException("Deadline dates should start with \"/by \"");
        }
        this.isDone = isDone;
        this.deadline = datePart.substring(3); // ignore "by "
        this.icon = "D";
    }

    /**
     * Returns a string representation of the task, including its completion status and deadline.
     *
     * @return A string in the format "[D][X] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Returns a string representation of the task formatted for storage.
     *
     * @return A string in the format "D | 0 | description | deadline", where the completion status is 0 or 1.
     */
    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description
                + " | " + deadline;
    }
}
