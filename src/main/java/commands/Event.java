package commands;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String createTaskList() {
        return  getStatusIcon() + " "+  description + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String createTaskTxt() {
        return "E | " + super.getStatus() + " | " + description + " | " + from + "-" + to;
    }
}
