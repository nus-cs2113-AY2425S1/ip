import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class for deadline objects.
 */
public class Deadline extends Task {
    protected String byString;
    protected LocalDate byDate;

    /**
     * Builder class for a deadline object.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task in string format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;

        try {
            this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            this.byDate = null;
        }
    }
    /**
     * Return the string format for the current deadline.
     *
     * @return the string format of the deadline task.
     */
    @Override
    public String toString() {
        String displayDate = (byDate != null) ? byDate.toString() : byString;
        return "[D]" + currentStatus() + " " + description + " (by: " + displayDate + ")";
    }

    /**
     * Return the to be saved format for the current deadline.
     *
     * @return the save format of the deadline task.
     */
    @Override
    public String toSave() {
        return "D |" + super.toSave() + " | " + byString;
    }

}