package task;
import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;

/**
 * Stores the information of a Deadline task
 */
public class Deadline extends Task{
    protected String by;

    private static String getDeadlineDescription(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        validityCheck(input);
        return input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1);
    }

    /**
     * Checks if the user input is valid with a description and a due date <br>
     * If user fails to input /by before the due date a {@code LeginMissingParamsException} will be thrown
     *
     * @param input User input in command line
     * @throws LeginMissingParamsException If missing due date
     * @throws LeginEmptyTaskException If no deadline task description
     */
    private static void validityCheck(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        int indexOfBy = input.indexOf("/by");
        if (indexOfBy == 9) {
            throw new LeginEmptyTaskException();
        }
        if (indexOfBy == -1 || input.length() == indexOfBy + 3 || input.length() == indexOfBy + 4) {
            throw new LeginMissingParamsException();
        }
    }

    public Deadline(String input) throws LeginEmptyTaskException, LeginMissingParamsException {
        super(getDeadlineDescription(input));
        int startingIndexOfDueDate = input.indexOf("/by") + 4;
        this.by = input.substring(startingIndexOfDueDate);
    }

    public Deadline(String description, String by) throws LeginEmptyTaskException {
        super(description);
        this.by = by;
    }

    /**
     * Formats the Deadline information to be stored into the storage text file
     *
     * @return Formatted data
     */
    @Override
    public String getWriteInfo() {
        return "D|" + isDone + "|" + task + "|" + by;
    }

    /**
     * Formats the Deadline information to be printed out in the command line
     *
     * @return Formatted data
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}