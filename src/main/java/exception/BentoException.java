package exception;

/**
 * This class provides custom exception handling for errors that occur during the
 * execution of Bento. It displays a default message or a user-defined message
 * when the exception is thrown.
 */
public class BentoException extends Exception {
    /** The default error message displayed when no specific message is provided. */
    public static final String DEFAULT_MESSAGE = "\tTASKETE\n";

    /** A line divider to format the error message output. */
    public static final String LINE_MESSAGE = "\t____________________________________________________________\n";

    /**
     * Constructs a new BentoException with the default error message.
     */
    public BentoException() {
        super(LINE_MESSAGE + DEFAULT_MESSAGE + LINE_MESSAGE);
    }

    /**
     * Constructs a new BentoException with a specified error message.
     *
     * @param message The custom message to display when the exception is thrown.
     */
    public BentoException(String message) {
        super(LINE_MESSAGE + message + LINE_MESSAGE);
    }
}
