package task;
import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;

/**
 * Stores information on an Event task
 */
public class Event extends Task{
    protected String eventStart;
    protected String eventEnd;

    private static String getEventDescription(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        validityCheck(input);
        return input.substring(input.indexOf(" ") + 1, input.indexOf("/from") - 1);
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
        if (indexOfFrom == 6) {
            throw new LeginEmptyTaskException();
        }
        if (indexOfFrom == -1 || indexOfTo == -1 || indexOfFrom + 5 == indexOfTo || indexOfFrom + 6 == indexOfTo) {
            throw new LeginMissingParamsException();
        }
    }

    public Event(String input) throws LeginEmptyTaskException, LeginMissingParamsException {
        super(getEventDescription(input));
        int startingIndexOfEventStart = input.indexOf("/from") + 6;
        int endingIndexOfEventStart = input.indexOf("/to") - 1;
        int startingIndexOfEventEnd = endingIndexOfEventStart + 5;
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