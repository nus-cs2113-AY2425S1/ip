package atom.exception;

/**
 * Represents an exception when the date in the <code>filter</code>
 * command is missing.
 */
public class MissingDateException extends RuntimeException {

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Please enter date to filter by.";
        return message;
    }
}
