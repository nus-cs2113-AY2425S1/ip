public class Event extends Task{

    protected String eventStartTime;
    protected String eventEndTime;

    public Event(String description, String eventStartTime, String eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String getTask(){
        return "[E]" + super.getTask() + " (" + eventStartTime + " " +eventEndTime + ")";
    }
}
