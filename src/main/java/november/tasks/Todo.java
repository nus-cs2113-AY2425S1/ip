package november.tasks;

/**
 * Represents a Todo task, which is a basic type of task with no specific deadlines or events.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description Describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints the confirmation message for an added Todo task.
     */
    @Override
    public void printTask() {
        System.out.println("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
    }

    /**
     * Returns the task icon specific to Todo tasks.
     *
     * @return The string "T" representing a Todo task.
     */
    @Override
    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "todo | " + isComplete + " | " + description;
    }
}
