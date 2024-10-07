package hsien.exception;

/**
 * Custom exception class for handling specific errors in the Hsien application.
 * This class extends the built-in Exception class.
 */
public class HsienException extends Exception{
    /**
     * Default constructor for HsienException.
     * Calls the parent constructor without a message.
     */
    public HsienException() {
        super();
    }

    /**
     * Constructor for HsienException with a custom error message.
     *
     * @param message the detail message for this exception.
     */
    public HsienException(String message) {
        super(message);
    }
}
