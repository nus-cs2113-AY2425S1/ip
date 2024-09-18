import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            throw new IllegalArgumentException("Error: Invalid date format. Please use 'yyyy-MM-dd HHmm' format.");
        }    }

    @Override
    public String toFileFormat() {
        return "D|" + (isDone() ? "1" : "0") + "|" + getDescription() + "|" + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}
