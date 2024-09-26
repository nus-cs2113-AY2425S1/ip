package quinn.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String dueDateTime;
    private final LocalDateTime parsedDueDateTime;

    public Deadline(String description, String dueDateTime) {
        this(description, dueDateTime, false);
    }

    public Deadline(String description, String dueDateTime, boolean isDone) {
        super(TaskType.DEADLINE, description, isDone);
        this.dueDateTime = dueDateTime;
        this.parsedDueDateTime = parseDateTime(dueDateTime); // parse dueDateTime into a LocalDateTime object
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
        if (parsedDueDateTime != null) {
            return super.toString() + " (by: " + formatDateTime(parsedDueDateTime) + ")";
        } else {
            return super.toString() + " (by: " + dueDateTime + ")";
        }
    }

    public String saveFormat() {
        if (parsedDueDateTime != null) {
            return super.saveFormat() + " | " + formatDateTime(parsedDueDateTime);
        } else {
            return super.saveFormat() + " | " + dueDateTime;
        }
    }
}
