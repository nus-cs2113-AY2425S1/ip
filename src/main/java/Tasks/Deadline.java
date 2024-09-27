package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline that the user wishes to jot down.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy H:mm");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
}
