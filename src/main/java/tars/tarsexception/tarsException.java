package tars.tarsexception;

/**
 * Custom exception class for handling errors specific to the TARS application.
 */
public class tarsException extends Exception {

    /**
     * Constructs a new tarsException with the specified detail message.
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public tarsException(String message) {
        super(message);
    }

}
