package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;
import freedom.exceptions.InvalidDateTime;
import freedom.exceptions.TimeEmpty;
import freedom.user.DateParser;

import java.time.LocalDateTime;

/**
 * Subclass of <code>Task</code> for storing additional related information for a task with duration.
 */
public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for <code>Event</code>.
     *
     * @param input User input excluding command.
     * @throws Exception If no task description is provided or no duration is provided.
     */
    public Event(String input) throws Exception {
        super(input);
        final int DESCRIPTION_INDEX = 0;
        final int FROM_INDEX = 1;
        final int TO_INDEX = 2;

        String[] components = input.split("/from|/to");
        updateDescription(components[DESCRIPTION_INDEX].trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
            setFrom(components[FROM_INDEX].trim());
            setTo(components[TO_INDEX].trim());
            if (getFrom().isEmpty() || getTo().isEmpty()) {
                throw new TimeEmpty();
            }
        } catch (DescriptionEmpty e) {
            ui.printEmptyDescriptionError();
            throw new Exception("Description is empty");
        } catch (TimeEmpty | ArrayIndexOutOfBoundsException e) {
            ui.printNoDuration();
            throw new Exception("No from/to time given");
        }
    }

    /**
     * Constructor for <code>Event</code>.
     *
     * @param description task description.
     * @param isDone status of task (done/ not done).
     * @param from start date and time in <code>String</code> format.
     * @param to end date and time in <code>String</code> format.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.isDone = isDone;
        setFrom(from);
        setTo(to);
    }

    /**
     * Sets the start date and time of the <code>Task</code>.
     *
     * @param from start date and time in <code>String</code> format.
     */
    public void setFrom(String from) {
        try {
            this.from = DateParser.convertToDateTime(from);
        } catch (InvalidDateTime e) {
            ui.printInvalidDateTime();
        }
    }

    /**
     * Returns start date and time in <code>String</code> format, in "dd MMM yyyy HHmm" form.
     */
    public String getFrom() {
        return DateParser.convertToString(from);
    }

    /**
     * Sets the start date and time of the <code>Task</code>.
     *
     * @param to end date and time in <code>String</code> format.
     */
    public void setTo(String to) {
        try {
            this.to = DateParser.convertToDateTime(to);
        } catch (InvalidDateTime e) {
            ui.printInvalidDateTime();
        }
    }

    /**
     * Returns end date and time in <code>String</code> format, in "dd MMM yyyy HHmm" form.
     */
    public String getTo() {
        return DateParser.convertToString(to);
    }

    /**
     * @inheritDoc
     * Includes task type symbol and duration.
     *
     * @return <code>String</code> with <code>Event</code> details.
     */
    public String generateTaskLine() {
        return "[E]" + super.generateTaskLine() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

    /**
     * @inheritDoc
     * Includes task type symbol and duration.
     *
     * @return <code>String</code> with <code>Event</code> details.
     */
    public String generateStorageLine() {
        return "E | " + super.generateStorageLine() + " | " + getFrom() + " | " + getTo() + "\n";
    }
}
