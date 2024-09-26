package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Parser.Parser;

public class Deadline extends Task {
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = Parser.parseDate(dueDate);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate.format(outputFormatter) + ")";
    }

    @Override
    public String getTaskMarker() {
        return "D";
    }

    @Override
    public String toFileFormat() {
        String status;
        if (isDone) {
            status = "1";
        } else {
            status = "0";
        }
        return "D | " + status + " | " + description + " (by: " + dueDate.format(outputFormatter) + ")";
    }
}