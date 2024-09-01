public class Event extends Task{
    protected String eventStart;
    protected String eventEnd;

    public Event(String input) {
        super(input.substring(input.indexOf(" ") + 1, input.indexOf("/from") - 1));
        int startingIndexOfEventStart = input.indexOf("/from") + 6;
        int endingIndexOfEventStart = input.indexOf("/to") - 1;
        int startingIndexOfEventEnd = endingIndexOfEventStart + 5;
        String eventStart = input.substring(startingIndexOfEventStart, endingIndexOfEventStart);
        String eventEnd = input.substring(startingIndexOfEventEnd);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart + " to: "
                + eventEnd + ")";

    }
}
