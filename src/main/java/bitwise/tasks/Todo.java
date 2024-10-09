package bitwise.tasks;

import bitwise.constants.Icons;

/**
 * The {@code Todo} class represents a task that does not have a specific time frame,
 * such as a simple to-do item. This class extends the {@code Task} class and includes
 * an icon to represent the to-do task.
 */
public class Todo extends Task {

    protected static final String symbol = Icons.ICON_TODO;

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description a {@code String} that describes the to-do item
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the {@code Todo} task, including its icon and description.
     *
     * @return a {@code String} that represents the {@code Todo} task
     */
    @Override
    public String toString() {
        return symbol + " " + super.toString();
    }
}
