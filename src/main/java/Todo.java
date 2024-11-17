/**
 * The Todo class represents a task that is a to-do item.
 * It extends the Task class to provide specific functionality for to-do tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its status and description.
     *
     * @return a formatted string representing the to-do task
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving to a file.
     *
     * @return a formatted string representing the to-do task for saving
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}