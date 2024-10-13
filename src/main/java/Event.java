public class Event extends Task {

    static final String typeIcon = "[E]";

    String eventStart;
    String eventEnd;


    public Event(String newName, String startTime, String endTime) {
        super(newName);
        eventStart = startTime;
        eventEnd = endTime;
    }

    @Override
    public String getEventStart() {
        return eventStart;
    }

    @Override
    public String getEventEnd() {
        return eventEnd;
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }


    // Irrelevant abstract methods declared in "Task" superclass
    public String getBy() {
        return null;
    }

}
