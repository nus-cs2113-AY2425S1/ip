package CodyChen.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The Deadline class represents a task with a deadline.
 * It extends the Todo class and manages the deadline information
 * associated with the task.
 */
public class Deadline extends Todo {

    protected String from_original;
    protected LocalDate from_parsed;
    /**
     * Constructs a Deadline object with the specified description
     * and original deadline string.
     *
     * @param description The description of the deadline task.
     * @param from The original deadline string.
     */
    public Deadline(String description, String from) {
        super(description);
        this.from_original = from;
        this.type = 'D';
    }
    /**
     * Constructs a Deadline object with the specified description
     * and parsed LocalDate for the deadline.
     *
     * @param description The description of the deadline task.
     * @param from The parsed LocalDate for the deadline.
     */
    public Deadline(String description, LocalDate from) {
        super(description);
        this.from_parsed = from;
        this.type = 'D';
    }
    /**
     * Returns the formatted deadline as a string.
     * If the deadline is a parsed LocalDate, it formats the date
     * in the "MMM dd yyyy" format. Otherwise, it returns the
     * original deadline string.
     *
     * @return The formatted deadline as a string.
     */
    @Override
    public String formattedDeadline() {
        if(from_parsed != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return from_parsed.format(formatter);
        }
        return from_original;
    }
}