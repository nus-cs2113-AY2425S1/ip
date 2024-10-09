package atom.exception;

/**
 * Represents an exception when errors are encountered in the ATOM class.
 */
public class AtomException extends RuntimeException{

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Whoops!! Something went wrong... :(";
        return message;
    }
}
