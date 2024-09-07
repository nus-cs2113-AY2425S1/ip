package Taylor.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String write(){
        return "D | " + (isCompleted? "1": "0") + " | " + description + " | " + by;
    }
}
