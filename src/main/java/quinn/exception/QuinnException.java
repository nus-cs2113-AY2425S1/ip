package quinn.exception;


/**
 * Represents a custom exception specific to the Quinn application.
 * This exception is thrown when application-specific errors occur during execution.
 *
 * QuinnException extends the standard Java {@link Exception} class,
 * allowing it to be caught and handled like any other exception in Java.
 *
 * This exception is typically used to encapsulate and report errors that are
 * specific to the logic or operations of the Quinn application,
 * providing more contextual information about what went wrong.
 *
 *  Common scenarios where this exception might be thrown include:
 *   Attempting to access or modify a non-existent task
 *   Invalid user input that cannot be processed by Quinn
 */
public class QuinnException extends Exception {
    /**
     * Constructs a new QuinnException with the specified error message.
     *
     * This constructor creates a QuinnException with a detailed message
     * describing the specific error condition that occurred in the Quinn application.
     *
     * @param errorMessage a detailed message describing the error condition
     *                     that led to this exception being thrown.
     */
    public QuinnException(String errorMessage) {
        super(errorMessage);
    }
}
