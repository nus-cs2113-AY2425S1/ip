package appal.exception;

public class AppalException extends Exception {
    public static final String MESSAGE = "Oh no... this is invalid input!";

    public AppalException() {
        super(MESSAGE);
    }

    public AppalException(String message) {
        super(message);
    }
}
