package jeremy.exception;

/**
 * Represents an exception thrown when there is an issue with the storage system.
 */
public class InvalidStorageException extends Exception {
    /**
     * Constructs an InvalidStorageException with the specified message.
     *
     * @param message The detail message explaining what caused the exception.
     */
    public InvalidStorageException(String message) {
        super(message);
    }
}
