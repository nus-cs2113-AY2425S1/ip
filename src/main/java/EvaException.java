/**
 * Represents a custom exception for the Eva application.
 * This exception is thrown to indicate errors related to
 * the task management operations in the Eva application.
 * It extends the built-in Exception class.
 */
public class EvaException extends Exception {
    public EvaException(String message) {
        super(message);
    }
}
