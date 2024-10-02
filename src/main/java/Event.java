import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from;

    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + currentStatus() + " " + description + " (from: " + from + " to: " + to + ")";

    }

    @Override
    public String toSave() {
        return "E |" + super.toSave() + " | " + from + " | " + to;
    }

}