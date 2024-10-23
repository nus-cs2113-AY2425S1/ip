import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class and includes a date by which the task is due.
 */
public class Deadline extends Task {
    protected LocalDate by;


    // Constructs a Deadline task with the specified description and deadline date.
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;;
    }


    // Converts the task details into a format suitable for saving to a file.
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    // Returns a string representation of the deadline task.
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
