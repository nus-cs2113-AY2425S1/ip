package luke.exceptions;

/**
 * Signals that the user has not provided enough arguments for the command to be executed.
 */
public class InsufficientArgumentsException extends LukeException {
    public InsufficientArgumentsException(String message) {
        super(message);
    }
}
