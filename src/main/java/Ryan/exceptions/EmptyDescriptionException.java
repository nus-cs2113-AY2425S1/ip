package Ryan.exceptions;

/**
 * Exception thrown when a task or query has an empty description.
 */
public class EmptyDescriptionException extends RyanException {

    /**
     * Constructs a new EmptyDescriptionException with the specified detail message.
     *
     */
    public EmptyDescriptionException() {
        super("Description field cannot be empty.");
    }
}
