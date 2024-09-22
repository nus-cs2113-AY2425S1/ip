package nova.task;

/**
 * Represents a Todo task that extends the base Task class.
 * A Todo task has a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * The task is initially not done and an acknowledgement message is printed.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
        printAcknowledgementMessage(getTaskInfo());
    }

    /**
     * Constructs a Todo task with the specified status and description.
     * The task is marked as done if the status string is "X".
     *
     * @param isDone     The status of the task ("X" for done, otherwise not done).
     * @param description The description of the Todo task.
     */
    public Todo(String isDone, String description) {
        super(description);
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Todo task, including its status icon and description.
     *
     * @return The Todo task information as a string.
     */
    @Override
    public String getTaskInfo() {
        return "[T][" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the Todo task for storage purposes.
     *
     * @return A string representing the Todo task for storage.
     */
    @Override
    public String getTaskStorageInfo() {
        return "T" + DIVIDER + this.getStatusIcon() + DIVIDER + description;
    }
}
