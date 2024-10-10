public class Event extends Task {
    String from;
    String to;

    /**
     * Represents an event task with a start time ("from") and an end time ("to").
     * Extends the Task class to include event-specific details.
     *
     * @param task the description of the event task
     * @param from the starting time of the event
     * @param to   the ending time of the event
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time of the event as a string
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time of the event as a string
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event, including the task description,
     * start time, and end time in the format "[E] task (from: start time to: end time)".
     *
     * @return a formatted string representing the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
