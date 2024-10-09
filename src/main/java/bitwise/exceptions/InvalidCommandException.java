package bitwise.exceptions;

/**
 * The {@code InvalidCommandException} class represents an exception that is thrown when a user inputs an invalid command.
 * This class extends {@code RuntimeException}, allowing it to be thrown during the normal operation of the Java Virtual Machine.
 */
public class InvalidCommandException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidCommandException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link Throwable#getMessage()} method
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
