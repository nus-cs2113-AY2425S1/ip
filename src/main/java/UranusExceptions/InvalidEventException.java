package UranusExceptions;

/**
 * Represents an exception that is thrown when the user
 * provides an invalid event format.
 * It extends the UranusExceptions class and provides
 * a specific error message with the correct event format.
 */
public class InvalidEventException extends UranusExceptions {
    /**
     * Constructs a new InvalidEventException.
     * This exception is triggered when the user provides an incorrect event format.
     */
    public InvalidEventException() {
        super();
    }

    /**
     * Returns a specific error message indicating that the event format is invalid.
     * Provides the correct format for the user to follow.
     *
     * @return A string message specifying the correct formats for events.
     */
    @Override
    public String getMessage() {
        return "Event format is invalid! \n" +
                "Correct format 1: event <task> /from <start> /to <end> \n" +
                "Correct format 2: " +
                "event <task> /from <date in dd-MM-yyyy> <time in HHmm>" +
                " /to <date in dd-MM-yyyy> <time in HHmm>";
    }
}
