package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import parser.Parser;

public class Deadline extends Task {
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = Parser.parseDate(dueDate);
    }

    /**
     * Returns the deadline task and due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate.format(outputFormatter) + ")";
    }

    /**
     * Retrieves the task type marker for Deadline tasks.
     *
     * @return The type marker for Deadline tasks.
     */
    @Override
    public String getTaskMarker() {
        return "D";
    }

    /**
     * Converts the Deadline task for saving to a file.
     *
     * @return A string representation of the Deadline task in file format.
     */
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