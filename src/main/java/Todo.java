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
     * Prints the Todo task in a specific format.
     */
    @Override
    public void printTask() {
        System.out.print("Got it. I've added this task:" + System.lineSeparator() + "  ");
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
}
