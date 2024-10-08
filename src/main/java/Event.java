import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * Inherits from the Task class and includes the event date, start time, and end time.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime from;
    protected LocalTime to;

    // Constructor accepting LocalDate for the event date and LocalTime for start and end times
    public Event(String description, LocalDate date, LocalTime from, LocalTime to) {
        super(description);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    // Converts the event details into a format suitable for saving to a file.
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + date + " | " + from + " | " + to;
    }

    // Returns a string representation of the event task.
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "[E]" + super.toString() + " (on: " + date.format(dateFormatter) + " from " + from.format(timeFormatter) + " to " + to.format(timeFormatter) + ")";
    }
}