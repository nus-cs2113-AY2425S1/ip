package UranusExceptions;

/**
 * Represents an exception that is thrown when the user
 * provides an invalid deadline format.
 * It extends the UranusExceptions class and provides
 * a specific error message with the correct deadline format.
 */
public class InvalidDeadlineException extends UranusExceptions {

    /**
     * Constructs a new InvalidDeadlineException.
     * This exception is triggered when the user provides an incorrect deadline format.
     */
    public InvalidDeadlineException() {
        super();
    }

    /**
     * Returns a specific error message indicating that the deadline format is invalid.
     * Provides the correct format for the user to follow.
     *
     * @return A string message specifying the correct formats for deadlines.
     */
    @Override
    public String getMessage() {
        return "Deadline format is invalid! \n" +
                "Correct format 1: deadline <task> /by <deadline> \n" +
                "Correct format 2: deadline <task> /by <date in dd-MM-yyyy> <time in HHmm>";
    }
}
