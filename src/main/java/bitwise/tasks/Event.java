package bitwise.tasks;

import bitwise.constants.Icons;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame, defined by a start and end time.
 * This class extends the {@code Task} class and includes additional information about the event's start and end times.
 */
public class Event extends Task {

    protected String eventFrom;
    protected String eventTo;
    protected static final String symbol = Icons.ICON_EVENT;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param description a {@code String} that describes the event
     * @param eventFrom a {@code String} representing the starting time of the event
     * @param eventTo a {@code String} representing the ending time of the event
     */
    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    /**
     * Returns a string representation of the {@code Event} task, including its symbol,
     * description, and the start and end times of the event.
     *
     * @return a {@code String} that represents the {@code Event} task
     */
    @Override
    public String toString() {
        return symbol + " " + super.toString() + "(from: " + eventFrom + " to: " + eventTo + ")";
    }
}
