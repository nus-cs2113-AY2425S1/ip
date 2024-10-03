package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;
import freedom.exceptions.InvalidDateTime;
import freedom.exceptions.TimeEmpty;
import freedom.user.DateParser;

import java.time.LocalDateTime;

/**
 * Subclass of <code>Task</code> for storing additional related information for a task with deadline.
 */
public class Deadline extends Task{
    protected LocalDateTime doneBy;

    /**
     * Constructor for <code>Deadline</code>.
     *
     * @param input User input excluding command.
     * @throws Exception If no task description is provided or no deadline is provided.
     */
    public Deadline(String input) throws Exception {
        super(input);
        final int DESCRIPTION_INDEX = 0;
        final int DONE_BY_INDEX = 1;

        String[] components = input.split("/by");
        updateDescription(components[DESCRIPTION_INDEX].trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
            setDoneBy(components[DONE_BY_INDEX].trim());
            if (getDoneBy().isEmpty()) {
                throw new TimeEmpty();
            }
        } catch (DescriptionEmpty e) {
            ui.printEmptyDescriptionError();
            throw new Exception("Description is empty");
        } catch (TimeEmpty | ArrayIndexOutOfBoundsException e) {
            ui.printNoDeadline();
            throw new Exception("No deadline given");
        }
    }

    /**
     * Constructor for <code>Deadline</code>.
     *
     * @param description task description.
     * @param isDone status of task (done/ not done).
     * @param doneByString deadline in <code>String</code> format.
     */
    public Deadline(String description, boolean isDone, String doneByString) {
        super(description);
        this.isDone = isDone;
        setDoneBy(doneByString);
    }

    /**
     * Returns deadline in <code>String</code> format, in "dd MMM yyyy HHmm" form.
     */
    public String getDoneBy() {
        return DateParser.convertToString(doneBy);
    }

    /**
     * Sets the deadline of the <code>Task</code>.
     *
     * @param doneBy deadline in <code>String</code> format.
     */
    public void setDoneBy(String doneBy) {
        try {
            this.doneBy = DateParser.convertToDateTime(doneBy);
        } catch (InvalidDateTime e) {
            ui.printInvalidDateTime();
        }
    }

    /**
     * @inheritDoc
     * Includes task type symbol and deadline.
     *
     * @return <code>String</code> with <code>Deadline</code> details.
     */
    public String generateTaskLine() {
        return "[D]" + super.generateTaskLine() + " (by: " + getDoneBy() + ")";
    }

    /**
     * @inheritDoc
     * Includes task type symbol and deadline.
     *
     * @return <code>String</code> with <code>Deadline</code> details.
     */
    public String generateStorageLine() {
        return "D | " + super.generateStorageLine() + " | " + getDoneBy() +  "\n";
    }
}
