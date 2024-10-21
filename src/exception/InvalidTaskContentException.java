package exception;

/**
 * This class represents an exception that is thrown when the input command is in wrong format.
 * It extends RuntimeException to indicate that it is an unchecked exception.
 */
public class InvalidTaskContentException extends RuntimeException {
    public InvalidTaskContentException(String message) {
        super(message);
    }
}
