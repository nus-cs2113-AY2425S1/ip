package Taylor.command;

/**
 * Represents a custom exception for the Taylor task management application.
 * TaylorException is thrown when there is an application-specific error.
 */
public class TaylorException extends Exception {

    /**
     * Constructs a new TaylorException with the specified detail message.
     *
     * @param message The detail message that describes the error.
     */
    public TaylorException(String message) {
        super(message);
    }
}