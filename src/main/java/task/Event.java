package task;
import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;

/**
 * Stores information on an Event task
 */
public class Event extends Task{
    private static final int INCREMENT_TO_DESCRIPTION_START = 1;
    private static final int DECREMENT_TO_DESCRIPTION_END = -1;
    private static final int INDEX_IF_MISSING_DESCRIPTION = 6;
    private static final int INDEX_IF_MISSING_PARAMS_WORD = -1;
    private static final int INCREMENT_FROM_START_OF_FROM = 6;
    private static final int INCREMENT_FROM_START_OF_FROM_WITH_SPACE= 6;
    private static final int DECREMENT_FROM_START_OF_TO = -1;
    private static final int INCREMENT_FROM_START_OF_TO = 4;
    protected String eventStart;
    protected String eventEnd;

    private static String getEventDescription(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        validityCheck(input);
        return input.substring(input.indexOf(" ") + INCREMENT_TO_DESCRIPTION_START,
                input.indexOf("/from") + DECREMENT_TO_DESCRIPTION_END);
    }

    /**
     * Checks if the user input is valid with a description and duration of event <br>
     * If user fails to input /from and /to before the stated duration a {@code LeginMissingParamsException} will be
     * thrown
     *
     * @param input User input in command line
     * @throws LeginMissingParamsException If missing duration of event
     * @throws LeginEmptyTaskException If no event task description
     */
    private static void validityCheck(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        int indexOfFrom = input.indexOf("from");
        int indexOfTo = input.indexOf("to");
        if (indexOfFrom == INDEX_IF_MISSING_DESCRIPTION) {
            throw new LeginEmptyTaskException();
        }
        if (indexOfFrom == INDEX_IF_MISSING_PARAMS_WORD || indexOfTo == INDEX_IF_MISSING_PARAMS_WORD
                || indexOfFrom + INCREMENT_FROM_START_OF_FROM == indexOfTo
                || indexOfFrom + INCREMENT_FROM_START_OF_FROM_WITH_SPACE == indexOfTo) {
            throw new LeginMissingParamsException();
        }
    }

    public Event(String input) throws LeginEmptyTaskException, LeginMissingParamsException {
        super(getEventDescription(input));
        int startingIndexOfEventStart = input.indexOf("/from") + INCREMENT_FROM_START_OF_FROM;
        int endingIndexOfEventStart = input.indexOf("/to") + DECREMENT_FROM_START_OF_TO;
        int startingIndexOfEventEnd = input.indexOf("/to") + INCREMENT_FROM_START_OF_TO;
        String eventStart = input.substring(startingIndexOfEventStart, endingIndexOfEventStart);
        String eventEnd = input.substring(startingIndexOfEventEnd);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public Event(String description, String eventStart, String eventEnd) throws LeginEmptyTaskException {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Formats the Event information to be stored into the storage text file
     *
     * @return Formatted data
     */
    @Override
    public String getWriteInfo() {
        return "E|" + isDone + "|" + task + "|" + eventStart + "|" + eventEnd;
    }

    /**
     * Formats the Event information to be printed out in the command line
     *
     * @return Formatted data
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart + " to: " + eventEnd + ")";
    }
}