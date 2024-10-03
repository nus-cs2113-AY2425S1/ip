package tasks;

/**
 * The {@code Events} class represents an event task that occurs at a specified time.
 * It extends the {@code Task} class and specifies the task type.
 */
public class Events extends Task {
    protected String from;

    /**
     * Constructs a new {@code Events} task with the specified description and time.
     *
     * @param description The description of the event task.
     * @param from The time at which the event occurs.
     */
    public Events(String description, String from) {
        super(description);
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "[E] ";
    }

    /**
     * Returns the time of the event.
     *
     * @return The time at which the event occurs.
     */
    public String getFrom() {
        return from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return (this.taskType() + "[" + super.getStatusIcon() + "] " + description + " from: " + from);
    }
}

