package bitwise.exceptions;

/**
 * The {@code InvalidFormatException} class represents an exception that is thrown when the format of a command or input is invalid.
 * This class extends {@code RuntimeException}, allowing it to be thrown during the normal operation of the Java Virtual Machine.
 */
public class InvalidFormatException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidFormatException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link Throwable#getMessage()} method
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
