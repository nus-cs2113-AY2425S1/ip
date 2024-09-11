public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
        final int FROM_INDEX = 1;
        final int TO_INDEX = 2;

        String[] components = description.split("/");
        setFrom(components[FROM_INDEX].replaceFirst("from ", ""));
        setTo(components[TO_INDEX].replaceFirst("to ", ""));
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
