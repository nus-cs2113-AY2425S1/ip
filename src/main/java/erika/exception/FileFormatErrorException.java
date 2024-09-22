package erika.exception;

/**
 * Represents the exception thrown when a Command has an empty description
 */
public class FileFormatErrorException extends ErikaException {
    /**
     * @param message A <code>String</code> representing the filename of the file that is not found
     */
    public FileFormatErrorException(String message) {
        super("File: " + message + "not found");
    }
}
