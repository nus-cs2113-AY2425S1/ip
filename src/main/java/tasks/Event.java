package tasks;

/**
 * The {@code Event} class represents a task that occurs over a specific time period,
 * starting at a defined start time and ending at a defined end time. This class
 * extends the abstract {@code Task} class, providing additional attributes and methods
 * for handling events.
 */
public class Event extends Task {

    static final String typeIcon = "[E]";

    String eventStart;
    String eventEnd;

    /**
     * Constructs a new {@code Event} task with the specified name, start time, and end time.
     *
     * @param newName the name or description of the event.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     */
    public Event(String newName, String startTime, String endTime) {
        super(newName);
        eventStart = startTime;
        eventEnd = endTime;
    }

    @Override
    public String getEventStart() {
        return eventStart;
    }

    @Override
    public String getEventEnd() {
        return eventEnd;
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }

    /**
     * A placeholder method for tasks that have a deadline. Since events do not have
     * deadlines, this method returns {@code null}.
     *
     * @return {@code null}, as events do not have a due date.
     */
    @Override
    public String getBy() {
        return null;
    }
}
