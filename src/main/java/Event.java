public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
    }
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static void createNewEvent(String userInput) throws InvalidCreateEventException {
        String[] eventInfo = userInput.split("/from|/to");
        if (eventInfo.length == 3) {
            Event event = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
            Aerus.tasks.add(event);
            UI.printContent("Added Event: " + event.toString());
        } else {
            throw new InvalidCreateEventException();
        }
    }

    @Override
    public void printMark() {
        UI.printContent("Nice! You have done this event:\n\t" + this.toString());
    }

    @Override
    public void printUnmark() {
        UI.printContent("I have unmarked this event:\n\t" + this.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveString() {
        return "E" + this.getStatusIcon() + "//" + this.description + "//" + this.from + "//" + this.to;
    }
}
