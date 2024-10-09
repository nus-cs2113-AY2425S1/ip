package Ryan.exceptions;

/**
 * Exception thrown when a task has an invalid format.
 */
public class InvalidStorageFormatException extends RyanException {
    /**
     * Constructs a new InvalidStorageFormatException with the specified detail message.
     *
     * @param line The task line that caused the exception.
     */
    public InvalidStorageFormatException(String line) {
        super("Invalid format: " + line);
    }
}
