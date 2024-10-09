package atom.exception;

/**
 * Represents an exception when the keyword in the <code>find</code>
 * command is missing.
 */
public class MissingKeywordException extends RuntimeException{

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "No keyword?! What am I supposed to look for then??";
        return message;
    }
}
