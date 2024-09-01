public class Event extends Task {
    private String start;
    private String end;

    //Event Constructor inherits Task
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    //Override toString method to show marker
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}