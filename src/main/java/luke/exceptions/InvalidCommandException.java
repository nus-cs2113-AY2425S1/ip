package luke.exceptions;

/**
 * Signals that the command provided by the user does not exist.
 */
public class InvalidCommandException extends LukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
