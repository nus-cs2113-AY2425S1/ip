package aether;

/**
 * Custom DukeException class for handling application-specific exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
