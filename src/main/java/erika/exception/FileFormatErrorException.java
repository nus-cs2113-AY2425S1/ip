package erika.exception;

/** Represents the exception thrown when a Command has an empty description. */
public class FileFormatErrorException extends ErikaException {
    public FileFormatErrorException(String message) {
        super("File: " + message + "not found");
    }
}
