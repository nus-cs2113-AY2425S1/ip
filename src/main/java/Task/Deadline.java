package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate dueDate;
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = parseDate(dueDate);
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(date, outputFormatter);
            } catch (DateTimeParseException e2) {
                return LocalDate.parse(date, formatter2);
            }
        }
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