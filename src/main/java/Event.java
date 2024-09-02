public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        setFrom(from.replaceFirst("from ", ""));
        setTo(to.replaceFirst("to ", ""));
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String printLine() {
        return "[E]" + super.printLine() + " (from: " + from + " to: " + to + ")";
    }
}
