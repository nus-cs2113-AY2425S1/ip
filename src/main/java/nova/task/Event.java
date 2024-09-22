package nova.task;

/**
 * Represents an Event task that extends the base Task class.
 * An Event task has a description, a completion status, and start and end times.
 */
public class Event extends Task {

    /**
     * The start time of the Event.
     */
    String from;

    /**
     * The end time of the Event.
     */
    String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     * The task is initially not done, and an acknowledgement message is printed.
     *
     * @param description The description of the Event task.
     * @param from       The start time of the Event task.
     * @param to         The end time of the Event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        printAcknowledgementMessage(getTaskInfo());
    }

    /**
     * Constructs an Event task with the specified status, description, start time, and end time.
     * The task is marked as done if the status string is "X".
     *
     * @param isDone     The status of the task ("X" for done, otherwise not done).
     * @param description The description of the Event task.
     * @param from       The start time of the Event task.
     * @param to         The end time of the Event task.
     */
    public Event(String isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Event task, including its status icon, description, start time, and end time.
     *
     * @return The Event task information as a string.
     */
    @Override
    public String getTaskInfo() {
        return "[E][" + this.getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event task for storage purposes.
     *
     * @return A string representing the Event task for storage.
     */
    @Override
    public String getTaskStorageInfo() {
        return "E" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + from + DIVIDER + to;
    }
}
