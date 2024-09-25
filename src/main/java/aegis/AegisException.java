package aegis;

/**
 * Represents an exception specific to the Aegis application.
 * This exception is used to handle errors that occur during the execution of the application,
 * providing a message that describes the specific issue encountered.
 */
public class AegisException extends Exception {

    /**
     * Constructs a new AegisException with the specified detail message.
     *
     * @param message The detail message which provides information about the exception.
     */
    public AegisException(String message) {
        super(message);
    }
}
