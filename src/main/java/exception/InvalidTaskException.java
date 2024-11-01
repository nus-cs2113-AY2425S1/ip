package exception;

/**
 * Represents an exception thrown when the user provides an invalid task command to Bento.
 * This class extends {@link BentoException} and is used to notify the user when a task-related
 * command is missing or improperly formatted.
 */
public class InvalidTaskException extends BentoException {
    /** The default message displayed when the user provides an invalid task command. */
    public static final String INVALID_TASK_MESSAGE = "\tHmm... That doesn't seem quite right. " +
            "Have you tried providing a task?\n";

    /**
     * Constructs a new InvalidTaskException with the default error message.
     */
    public InvalidTaskException() {
        super(INVALID_TASK_MESSAGE);
    }
}
