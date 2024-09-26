package Task;

import AlyBot.AlyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected String formattedStartTime;
    protected String formattedEndTime;

    public Event(String description, String startTime, String endTime) throws AlyException {
        super(description);
        try {
            this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
            formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new AlyException("Invalid Date lah bro, can use your brain anot?!");
        }
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
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }
}
