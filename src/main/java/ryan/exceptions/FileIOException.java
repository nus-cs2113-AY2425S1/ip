package ryan.exceptions;

/**
 * Exception thrown when a file I/O operation fails.
 */
public class FileIOException extends RyanException {
    /**
     * Constructs a new FileIOException with the specified error message.
     *
     * @param message The error message explaining the reason for the I/O failure.
     */
    public FileIOException(String message) {
        super("Error: " + message);
    }
}

