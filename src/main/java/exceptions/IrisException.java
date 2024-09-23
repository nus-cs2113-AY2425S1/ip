package exceptions;

/**
 * The IrisException class represents a custom exception for the Iris application.
 * It extends the built-in Exception class to provide specific error handling
 * related to the application’s functionality.
 *
 * @author Tan Ping Hui
 */
public class IrisException extends Exception {

    /**
     * Constructs an IrisException with the specified detail message.
     *
     * @param message The detail message to be associated with this exception.
     */
    public IrisException(String message) {
        super(message);
    }
}
