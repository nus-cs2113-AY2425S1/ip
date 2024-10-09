package atom.exception;

/**
 * Represents an exception when the task name in <code>deadline</code>
 * command is empty.
 */
public class EmptyDeadlineException extends RuntimeException {

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Huh?? Cannot identify deadline task..";
        return message;
    }
}
