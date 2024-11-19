package Taylor.task;

/**
 * Represents an Event task, which is a task that occurs during a specific time range.
 */
public class Event extends Task {

    String from;
    String to;

    /**
     * Constructs an Event task with the specified description and time range.
     *
     * @param description The description of the Event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with the specified description, completion status, and time range.
     *
     * @param description The description of the Event task.
     * @param isComplete The completion status of the Event task (true if completed, false otherwise).
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, Boolean isComplete, String from, String to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, which includes the task type, description,
     * and the event's time range.
     *
     * @return A string in the format "[E][ ] task description (from: start_time to: end_time)"
     *         where "[E]" indicates an Event task, "[ ]" or "[X]" indicates whether the task is completed,
     *         and the start and end times are displayed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string suitable for saving to a file, representing the Event task's data.
     *
     * @return A string in the format "E | 0 | task description | start_time-end_time"
     *         where "E" indicates an Event task, "0" or "1" indicates whether the task is completed,
     *         and the task description, start time, and end time are stored.
     */
    @Override
    public String write() {
        return "E | " + (isCompleted ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }
}