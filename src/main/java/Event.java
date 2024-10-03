import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Constructs an Event with the specified description, from, and to time.
     *
     * @param description The description of the event.
     * @param from The starting time of the event in 'yyyy-MM-dd HHmm' format.
     * @param to The ending time of the event in 'yyyy-MM-dd HHmm' format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: "
                + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
    }

    /**
     * Returns a string representation for saving the event task to a file.
     *
     * @return A formatted string of the event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(inputFormatter) + " | " + to.format(inputFormatter);
    }
}
