package melchizedek.task;

public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructor of the Event class.
     *
     * @param description Event description
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Constructor of the Event class,
     * mainly used when loading tasks from save file only.
     *
     * @param description Event description
     * @param isDone Truth value of whether event has been marked as done
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Method to convert event to the save file format.
     *
     * @return Event in formatted String
     */
    @Override
    public String taskToFile() {
        return "E | " + super.taskToFile() + " | " + from + " | " + to;
    }

    /**
     * Override method to format event to printable String
     *
     * @return Event in formatted String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
