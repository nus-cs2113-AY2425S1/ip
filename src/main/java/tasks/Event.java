package tasks;

/**
 * Event class inherits from {@code Task} with similar methods.
 * Handles tasks with a start and end period.
 */
public class Event extends Task {
    private String from;
    private String to;
    /**
     * Default constructor for new Events
     * @param task the task description
     * @param from the start time of the task
     * @param to the end time of the task
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    /**
     * Constructor used when decoding tasks from storage.
     * @param task the task description
     * @param isDone the task status
     * @param from the start time of the task
     * @param to the end time of the task
     */
    public Event(String task, boolean isDone, String to, String from) {
        super(task, isDone);
        this.to = to;
        this.from = from;
    }

    public String getTypeMarker() {
        return "[E]";
    }

    @Override
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (from: %s to: %s)\n", this.from, this.to);
    }
    @Override
    public String toString() {
        return String.format("%s | %b | %s | %s | %s", getTypeMarker(), this.isDone,
                this.task, this.from, this.to);
    }
}
