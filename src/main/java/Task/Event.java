package Task;

import AlyBot.AlyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected String formattedStartDateTime;
    protected String formattedEndDateTime;

    public Event(String description, String startTime, String endTime) throws AlyException {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
            formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new AlyException("Invalid Date lah bro, can use your brain anot?!");
        }
    }

    public String getFormattedStartDateTime() {
        return formattedStartDateTime;
    }

    public String getFormattedEndDateTime() {
        return formattedEndDateTime;
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
        return "[E]" + super.toString() + " (from: " + formattedStartDateTime + " to: " + formattedEndDateTime + ")";
    }
}
