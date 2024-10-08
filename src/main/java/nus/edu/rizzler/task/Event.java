package nus.edu.rizzler.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String taskName, Boolean isDone, String from, String to) {
        super(taskName, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    @Override
    public String toCSV(){
        return String.format("E, %s, %s, %s", super.toCSV(), from, to);
    }
}
