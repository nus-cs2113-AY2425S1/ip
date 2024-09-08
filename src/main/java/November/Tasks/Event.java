package November.Tasks;

/**
 * Represents an Event task, which is a task that occurs within a specific time frame.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description Describes the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Prints the confirmation message for an added Event task.
     */
    @Override
    public void printTask() {
        System.out.print("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
        System.out.println(" (from:" + start + " to:" + end + ")");
    }

    /**
     * Returns the task icon specific to Event tasks.
     *
     * @return The string "E" representing an Event task.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }
}
