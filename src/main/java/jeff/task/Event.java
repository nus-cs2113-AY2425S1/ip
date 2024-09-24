package jeff.task;

import jeff.exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) throws InvalidFormatException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Event date inputs are not formatted correctly...");
        }
    }

    public String fileDate(LocalDate field) {
        return field.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String userDate(LocalDate field) {
        return field.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String fileContent() {
        return "E" + super.fileContent() + " | " + fileDate(from) + " | " + fileDate(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + userDate(from) + " to: " + userDate(to) + ")";
    }
}
