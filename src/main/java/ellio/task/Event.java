package ellio.task;

public class Event extends Task{

    protected String eventStartTime;
    protected String eventEndTime;

    public Event(String description, String isDone, String eventStartTime, String eventEndTime) {
        super(description, isDone);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String getTaskInfo(){
        return "[E]" + super.getTaskInfo() + " (" + eventStartTime + " " +eventEndTime + ")";
    }

    public String getSaveFileTask(){
        return " e " + super.getSaveFileTask() + " | " + eventStartTime + " | " + eventEndTime;
    }
}
