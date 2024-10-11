/**
 * Represents an Event task in the KBot application.
 * An Event task has a start time and an end time, indicating when the event occurs.
 */
public class Event extends Task {
    private String from; // The start time of the event
    private String to;   // The end time of the event

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param from       The starting time of the event.
     * @param to         The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description, null); // No direct time field; we'll use from and to
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task to a string format for file storage.
     * The format is: E | isDone | description | from | to
     *
     * @return A string representation of the Event task for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " to: " + to;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}
