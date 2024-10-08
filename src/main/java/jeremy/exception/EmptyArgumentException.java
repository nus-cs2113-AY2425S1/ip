package jeremy.exception;

/**
 * Represents an exception thrown when a required argument, such as a task description, is empty.
 */
public class EmptyArgumentException extends JeremyException {
    /**
     * Constructs an EmptyArgumentException with the specified message.
     *
     * @param message The detail message explaining what caused the exception.
     */
    public EmptyArgumentException(String message) {
        super(message + " cannot be empty");
    }
}
