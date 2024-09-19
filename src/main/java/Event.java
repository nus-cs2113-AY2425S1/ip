public class Event extends Task {

    static String typeIcon = "[E]";

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
}
