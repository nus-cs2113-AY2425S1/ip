package erika.task;

import erika.settings.Settings;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 * Inherits Task class
 */
public class Deadline extends Task {
    protected String deadline = null;
    protected LocalDate deadlineDate = null;
    protected LocalDateTime deadlineDateTime = null;

    /**
     * Constructor for <code>Deadline</code> class.
     *
     * @param description <code>String</code> that represents the description of the deadline
     * @param deadline <code>String</code> that represents a textual representation of the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        getDeadline(deadline);
    }

    private void getDeadline(String by) {
        try {
            this.deadlineDateTime = Settings.parseLocalDateTime(by);
        } catch (DateTimeParseException e1) {
            try {
                this.deadlineDate = Settings.parseLocalDate(by);
            } catch (DateTimeParseException e2) {
                this.deadline = by;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", description,
                (deadlineDateTime != null) ? deadlineDateTime.format(Settings.DATE_TIME_OUT_FORMATTER) :
                        (deadlineDate != null) ? deadlineDate.format(Settings.DATE_OUT_FORMATTER) : deadline);
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format
     * for writing to text file.
     */
    @Override
    public String generateFileLine() {
        return String.format("D,%s,%s,%s\n", isDone ? "1" : "0", description, (deadlineDateTime != null) ?
                deadlineDateTime.format(Settings.DATE_TIME_IN_FORMATTER) : (deadlineDate != null) ?
                deadlineDate.format(Settings.DATE_IN_FORMATTER) : deadline);
    }
}
