package Glendon.task;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(int completed, String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        if (completed == 1) {
            super.isCompleted = true;
        }
    }

    @Override
    public String saveToFile() {
        return "E|" + super.saveToFile() + "|" + start + "|" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }
}
