package king;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected final String taskStartTime = "NIL";
    protected String taskEndTime;
    protected final String TASKTYPEICON = "[D]";
    private LocalDateTime deadlineDateTime;

    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (by: " + taskEndTime + ")";
    }

    public LocalDateTime getDeadlineDateTime() {
        return deadlineDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a MMM dd yyyy");
        return "[D]" + getStatusIcon() + description + " (by: " + deadlineDateTime.format(formatter) + ")";
    }
}
