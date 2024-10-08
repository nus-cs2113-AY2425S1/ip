package jeremy.task;

import jeremy.exception.EmptyArgumentException;

/**
 * Represents a simple task without any specific time frame.
 * The task contains only a description and an optional completion status.
 */
public class Todo extends Task {
    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description The task description.
     * @throws EmptyArgumentException If the task description is empty or blank.
     */
    public Todo(String description) throws EmptyArgumentException {
        super(description, "T");

        if (description.isBlank()) {
            throw new EmptyArgumentException("Todo description");
        }
    }

    /**
     * Returns a string representation of the Todo task, including its completion status.
     *
     * @return A string in the format "[T][X] description".
     */
    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task formatted for storage.
     *
     * @return A string in the format "T | 0 | description", where the completion status is 0 or 1.
     */
    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description;
    }
}