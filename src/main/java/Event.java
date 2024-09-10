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

    public static void createNewEvent(String userInput) {
        String[] eventInfo = userInput.split("/from|/to");
        Event event = new Event(eventInfo[0]);
        if (eventInfo.length >= 2) {
            event.from = eventInfo[1].strip();
        }
        if (eventInfo.length >= 3) {
            event.to = eventInfo[2].strip();
        }
        Aerus.tasks[Task.tasksCount-1] = event;
        UI.printContent("Added Event: " + event.toString());
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
}
