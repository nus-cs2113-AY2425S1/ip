package Ryan.exceptions;

/**
 * Exception thrown when an unknown task type is encountered.
 */
public class UnknownTaskTypeException extends RyanException {

    /**
     * Constructs a new UnknownTaskTypeException with the specified task type.
     *
     * @param type The unrecognized task type.
     */
    public UnknownTaskTypeException(String type) {
        super("Unknown task type: " + type);
    }
}
