package exception;

/**
 * Represents an exception thrown when a requested task cannot be found in Bento.
 * This class extends {@link BentoException} and is used to notify the user when they attempt to
 * access a task that does not exist.
 */
public class MissingTaskException extends BentoException {
    /** The default message displayed when a requested task cannot be found. */
    public static final String NO_TASK_FOUND_MESSAGE = "\tHmm... I don't think that task exists... " +
            "Check again with list!\n";

    /**
     * Constructs a new MissingTaskException with the default error message.
     */
    public MissingTaskException() {
        super(NO_TASK_FOUND_MESSAGE);
    }
}
