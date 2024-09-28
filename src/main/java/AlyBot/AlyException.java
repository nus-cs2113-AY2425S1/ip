package AlyBot;

/**
 * Represents an exception specific to the AlyBot application.
 * This is used for handling various errors that occur during execution.
 */
public class AlyException extends Exception {

    /**
     * Constructs an AlyException with a specified error message.
     *
     * @param message The error message.
     */
    public AlyException(String message) {
        super(message);
    }

    /**
     * Constructs an AlyException with a specified error message and cause.
     *
     * @param message The error message.
     * @param cause   The underlying cause of the exception.
     */
    public AlyException(String message, Throwable cause) {
        super(message, cause);
    }
}