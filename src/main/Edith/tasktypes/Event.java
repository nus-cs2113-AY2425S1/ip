package tasktypes;

/**
 * Represents a type of task (it is-a Task)
 * which contains a description, a start time and a end time
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description, TypeOfTask.Event);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, TypeOfTask.Event, isDone);
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a character signifying the type of task.
     *
     * @return Character 'E' signifying the type of task the object is
     */
    public char getTaskCharacter() {
        return 'E';
    }

    /**
     * Returns the current state of the object
     * in a format which can be directly written to a file
     *
     * @return a string which is in a storable format
     */
    public String getStorableString() {
        int isDoneInteger = getIsDone() ? 1 : 0;
        return getTaskCharacter() + " | " + isDoneInteger +
                " | " + getDescription() + " | " + getFrom() + " - " + getTo();
    }
}
