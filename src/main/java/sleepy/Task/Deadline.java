package sleepy.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    // Update the constructor to accept LocalDateTime instead of parsing inside the constructor
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        // Save the date-time in the "MMM dd yyyy, h:mm a" format
        return "D|" + (isDone() ? "1" : "0") + "|" + getDescription() + "|" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        // Display the date-time in the "MMM dd yyyy, h:mm a" format
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}
