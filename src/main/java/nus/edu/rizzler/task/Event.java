package nus.edu.rizzler.task;

/**
 * Represents an event task, which includes a description, start time, and end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an {@code Event} task with the specified name, completion status, start time, and end time.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String taskName, Boolean isDone, String from, String to) {
        super(taskName, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, formatted for display.
     *
     * @return A string representing the event task with its start and end times.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    /**
     * Returns a CSV-formatted string representation of the event task.
     *
     * @return A CSV string representing the event task with its start and end times.
     */
    @Override
    public String toCSV(){
        return String.format("E, %s, %s, %s", super.toCSV(), from, to);
    }
}
