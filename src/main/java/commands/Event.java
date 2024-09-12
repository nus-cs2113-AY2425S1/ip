package commands;

public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[E]"+ super.getStatusIcon();
    }
}
