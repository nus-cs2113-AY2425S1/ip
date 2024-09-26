package quinn.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;
    private final LocalDateTime parsedStartDateTime;
    private final LocalDateTime parsedEndDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        this(description, startDateTime, endDateTime, false);
    }

    public Event(String description, String startDateTime, String endDateTime, boolean isDone) {
        super(TaskType.EVENT, description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.parsedStartDateTime = parseDateTime(startDateTime); // parse startDateTime into a LocalDateTime object
        this.parsedEndDateTime = parseDateTime(endDateTime); // parse endDateTime into a LocalDateTime object
    }

    private LocalDateTime parseDateTime(String inputDateTime) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(inputDateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            return null; // if inputDateTime is not in "yyyy-MM-dd HHmm" format
        }
    }

    private String formatDateTime(LocalDateTime parsedDateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return parsedDateTime.format(outputFormatter);
    }

    @Override
    public String toString() {
        if (parsedStartDateTime != null && parsedEndDateTime != null) {
            return super.toString()
                    + " (from: " + formatDateTime(parsedStartDateTime)
                    + " to: " + formatDateTime(parsedEndDateTime)  + ")";
        } else {
            return super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
        }
    }

    public String saveFormat() {
        if (parsedStartDateTime != null && parsedEndDateTime != null) {
            return super.saveFormat()
                    + " | " + formatDateTime(parsedStartDateTime)
                    + " | " + formatDateTime(parsedEndDateTime);
        } else {
            return super.saveFormat() + " | " + startDateTime + " | " + endDateTime;
        }
    }
}
