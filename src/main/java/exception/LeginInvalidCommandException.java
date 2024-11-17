package exception;

/**
 * Signals that the user has input a command that does not exist
 */
public class LeginInvalidCommandException extends LeginException{
    private static final String ERROR_MESSAGE = "BROO! No such command!";
    public LeginInvalidCommandException() {
        super(ERROR_MESSAGE);
    }
}
