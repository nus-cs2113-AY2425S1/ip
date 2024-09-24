package Task;

public class Event extends Task {
    private String start;
    private String end;

    //task.Event Constructor inherits task.Task
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String getTaskMarker() {
        return "E";
    }

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