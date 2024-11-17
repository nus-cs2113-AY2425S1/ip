package exception;

/**
 * Signals that the user failed to input all arguments required for the type of task <br>
 * Usually thrown due to missing time stamps or duration in input
 */
public class LeginMissingParamsException extends LeginException {
    private static final String ERROR_MESSAGE = "BROO! Missing Parameters!";
    public LeginMissingParamsException() {
        super(ERROR_MESSAGE);
    }
}
