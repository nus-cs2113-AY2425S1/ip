package exception;

public class LeginMissingParamsException extends LeginException {
    private static final String ERROR_MESSAGE = "BROO! Missing Parameters!";
    public LeginMissingParamsException() {
        super(ERROR_MESSAGE);
    }
}
