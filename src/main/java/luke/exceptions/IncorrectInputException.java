package luke.exceptions;

/**
 * Signals that the input provided by the user is incorrect.
 */
public class IncorrectInputException extends LukeException {
    public IncorrectInputException(String message) {
        super(message);
    }
}
