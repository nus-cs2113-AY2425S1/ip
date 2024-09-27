package king;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String taskStartTime;
    protected String taskEndTime;
    protected final String TASKTYPEICON = "[E]";
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (from: " + taskStartTime + " to: " + taskEndTime + ")";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a MMM dd yyyy");
        return "[E]" + getStatusIcon() + description + " (from: " + startTime.format(formatter) + " to: " + endTime.format(formatter) + ")";
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
