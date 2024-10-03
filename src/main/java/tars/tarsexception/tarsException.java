package tars.tarsexception;

/**
 * Represents a custom exception for the TARS application.
 * This exception is used to handle specific errors related to TARS commands and operations.
 */
public class TarsException extends Exception {

    /**
     * Constructs a new TarsException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public TarsException(String message) {
        super(message);
    }

}
