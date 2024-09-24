package jeff.task;

/**
 * Represents an event task in the task management system.
 * The <code>Event</code> class extends the <code>Task</code> class to provide
 * specific functionalities related to event tasks, including the start and end times.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task's content for file storage.
     * This includes a prefix "E" followed by the task's status number, description,
     * start time, and end time.
     *
     * @return A string formatted for file storage, indicating the task type as "E".
     */
    @Override
    public String fileContent() {
        return "E" + super.fileContent() + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the event task.
     * This includes a prefix "[E]" to indicate the task type as an event task,
     * followed by the task's status icon, description, start time, and end time.
     *
     * @return A string that represents the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
