package task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the event task and its start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Retrieves the task type marker for Event tasks.
     *
     * @return The type marker for Event tasks.
     */
    @Override
    public String getTaskMarker() {
        return "E";
    }

    /**
     * Converts the Event task for saving to a file.
     *
     * @return A string representation of the Event task in file format.
     */
    @Override
    public String toFileFormat() {
        String status;
        if (isDone) {
            status = "1";
        } else {
            status = "0";
        }
        return "E | " + status + " | " + description + " (from: "+ start + " to: " + end + ")";
    }
}