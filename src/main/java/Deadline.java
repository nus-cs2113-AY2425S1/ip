import exception.InvalidCreateDeadlineException;

/**
 * Represent Deadlines with a description and a <code>by</code> field.
 * Inherits from <code>Task</code> class.
 */

public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new Deadline object with the given description.
     *
     * @param description The description of the deadline.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Constructs a new Deadline object with the given description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date for the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Reformats user input to create a new deadline.
     *
     * @param userInput The user input for creating the deadline.
     * @throws InvalidCreateDeadlineException If the input format is invalid.
     */
    public static void createNewDeadline(String userInput) throws InvalidCreateDeadlineException {
        String[] deadlineInfo = userInput.split("/by");

        if (deadlineInfo.length == 2) {
            Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1].strip());
            TaskList.tasks.add(deadline);
            UI.printContent("Added Deadline: " + deadline.toString());
        } else {
            throw new InvalidCreateDeadlineException();
        }

    }

    /**
     * Prints a message indicating that the deadline has been marked as done.
     */
    @Override
    public void printMark() {
        UI.printContent("Nice! You have done this deadline:\n\t" + this.toString());
    }

    /**
     * Prints a message indicating that the deadline has been unmarked.
     */
    @Override
    public void printUnmark() {
        UI.printContent("I have unmarked this deadline:\n\t" + this.toString());
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return A formatted string representing the deadline.
     */
    @Override
    public String toString() {
        String output = "[D]" + super.toString();
        if (by != null) {
            output += " (by: " + by + ")";
        }
        return output;
    }

    /**
     * Generates a string for saving the deadline with Data Manager.
     *
     * @return A formatted string for saving the deadline.
     */
    @Override
    public String toSaveString() {
        return "D" + this.getStatusIcon() + "//" + this.description + "//" + this.by;
    }
}
