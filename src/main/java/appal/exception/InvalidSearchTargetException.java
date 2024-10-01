package appal.exception;

public class InvalidSearchTargetException extends AppalException {
    public static final String INVALID_SEARCH_TARGET_MESSAGE = "Please input a valid search target!" +
            "\nExample input: find [SEARCH_TARGET]";

    public InvalidSearchTargetException() {
        super(INVALID_SEARCH_TARGET_MESSAGE);
    }
}
