package exception;

/**
 * Represents an exception thrown when no tasks are found matching the user's search criteria in Bento.
 * This class extends {@link BentoException} and is used to notify the user
 * when their search for tasks yields no results.
 */
public class NoTaskFoundException extends BentoException {
    /** The default message displayed when no tasks matching the search criteria are found. */
    public static final String NO_TASK_FOUND_MESSAGE = "\tHmm... I couldn't find any of the tasks that you " +
            "were searching for, maybe they don't exist?\n";

    /**
     * Constructs a new NoTaskFoundException with the default error message.
     */
    public NoTaskFoundException() {
        super(NO_TASK_FOUND_MESSAGE);
    }
}
