package yapper.exceptions;

/**
 * The {@code YapperException} class is a custom exception that is thrown
 * to indicate errors specific to the Yapper chatbot application.
 */
public class YapperException extends Exception {

    /**
     * Constructs a new {@code YapperException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval
     *                by the {@code getMessage()} method
     */
    public YapperException(String message) {
        super(message);
    }
}
