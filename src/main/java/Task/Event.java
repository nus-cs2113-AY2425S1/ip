package Task;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFile(String taskDescription, char status) {
        int fromIndex = taskDescription.indexOf("(from: ");
        int toIndex = taskDescription.indexOf("to: ");
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(6,fromIndex-1) + "|"
                + taskDescription.substring(fromIndex+7,toIndex) + "|"
                + taskDescription.substring(toIndex+4,taskDescription.length()-1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
