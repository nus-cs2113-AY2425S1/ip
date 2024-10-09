package atom.exception;

/**
 * Represents an exception when the task name in <code>event</code>
 * command is empty.
 */
public class EmptyEventException extends RuntimeException {

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Guess what's missing?? Your event task silly!!";
        return message;
    }
}
