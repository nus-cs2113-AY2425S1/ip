package nova.task;

import nova.Storage;

/**
 * Represents a Deadline task that extends the base Task class.
 * A Deadline task has a description, a completion status, and a due date.
 */
public class Deadline extends Task {

    /**
     * The due date for the Deadline task.
     */
    String by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * The task is initially not done, and an acknowledgement message is printed.
     *
     * @param description The description of the Deadline task.
     * @param by          The due date for the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        printAcknowledgementMessage(getTaskInfo());
    }

    /**
     * Constructs a Deadline task with the specified status, description, and due date.
     * The task is marked as done if the status string is "X".
     *
     * @param isDone     The status of the task ("X" for done, otherwise not done).
     * @param description The description of the Deadline task.
     * @param by          The due date for the Deadline task.
     */
    public Deadline(String isDone, String description, String by) {
        super(description);
        this.by = by;
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its status icon, description, and due date.
     *
     * @return The Deadline task information as a string.
     */
    @Override
    public String getTaskInfo() {
        return "[D][" + this.getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task for storage purposes.
     *
     * @return A string representing the Deadline task for storage.
     */
    @Override
    public String getTaskStorageInfo() {
        return "D" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + by;
    }
}
