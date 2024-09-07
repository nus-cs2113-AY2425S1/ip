package task;
import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;

public class Event extends Task{
    protected String eventStart;
    protected String eventEnd;

    private static String getEventDescription(String input) throws LeginMissingParamsException,
            LeginEmptyTaskException {
        validityCheck(input);
        return input.substring(input.indexOf(" ") + 1, input.indexOf("/from") - 1);
    }

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
        System.out.println(startingIndexOfEventStart);
        int endingIndexOfEventStart = input.indexOf("/to") - 1;
        int startingIndexOfEventEnd = endingIndexOfEventStart + 5;
        String eventStart = input.substring(startingIndexOfEventStart, endingIndexOfEventStart);
        String eventEnd = input.substring(startingIndexOfEventEnd);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart + " to: " + eventEnd + ")";
    }
}
