package task;
import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;

/**
 * Stores the information of a Deadline task
 */
public class Deadline extends Task{
    private static final int INCREMENT_TO_DESCRIPTION_START = 1;
    private static final int DECREMENT_TO_DESCRIPTION_END = 1;
    private static final int INDEX_IF_NO_DESCRIPTION = 9;
    private static final int INDEX_IF_MISSING_WORD_BY = -1;
    private static final int SIZE_OF_WORD_BY = 3;
    private static final int SIZE_OF_WORD_BY_AND_SPACE = 4;
    private static final int INCREMENT_TO_BY_START = 4;
    protected String duedate;

    private static String getDeadlineDescription(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        checkValidity(input);
        return input.substring(input.indexOf(" ") + INCREMENT_TO_DESCRIPTION_START,
                input.indexOf("/by") - DECREMENT_TO_DESCRIPTION_END);
    }

    /**
     * Checks if the user input is valid with a description and a due date <br>
     * If user fails to input /by before the due date a {@code LeginMissingParamsException} will be thrown
     *
     * @param input User input in command line
     * @throws LeginMissingParamsException If missing due date
     * @throws LeginEmptyTaskException If no deadline task description
     */
    private static void checkValidity(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        int indexOfBy = input.indexOf("/by");
        if (indexOfBy == INDEX_IF_NO_DESCRIPTION) {
            throw new LeginEmptyTaskException();
        }
        if (indexOfBy == INDEX_IF_MISSING_WORD_BY || input.length() == indexOfBy + SIZE_OF_WORD_BY
                || input.length() == indexOfBy + SIZE_OF_WORD_BY_AND_SPACE) {
            throw new LeginMissingParamsException();
        }
    }

    public Deadline(String input) throws LeginEmptyTaskException, LeginMissingParamsException {
        super(getDeadlineDescription(input));
        int startingIndexOfDueDate = input.indexOf("/by") + INCREMENT_TO_BY_START;
        this.duedate = input.substring(startingIndexOfDueDate);
    }

    public Deadline(String description, String duedate) throws LeginEmptyTaskException {
        super(description);
        this.duedate = duedate;
    }

    /**
     * Formats the Deadline information to be stored into the storage text file
     *
     * @return Formatted data
     */
    @Override
    public String getWriteInfo() {
        return "D|" + isDone + "|" + task + "|" + duedate;
    }

    /**
     * Formats the Deadline information to be printed out in the command line
     *
     * @return Formatted data
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + duedate + ")";
    }
}