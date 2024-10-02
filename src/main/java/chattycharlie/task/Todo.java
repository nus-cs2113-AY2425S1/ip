package chattycharlie.task;

import chattycharlie.commands.CommandType;

/**
 * Represents a simple to-do task without any date or time constraints.
 * A <code>Todo</code> task has only a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a <code>Todo</code> task with the specified description.
     *
     * @param description the description of the to-do task.
     */
    public Todo(String description) {
        super(description, CommandType.TODO);
    }

    /**
     * Returns a string representation of the to-do task, including its marked status and description.
     *
     * @return a string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving.
     *
     * @return a string representation of the to-do task formatted for saving.
     */
    @Override
    public String toSaveFormat() {
        return "[T]" + super.toSaveFormat();
    }
}
