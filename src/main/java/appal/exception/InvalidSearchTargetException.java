package appal.exception;

/**
 * InvalidDeadlineException handles the error when user did not input the search target string,
 * and shows a message to notify user.
 */
public class InvalidSearchTargetException extends AppalException {
    public static final String INVALID_SEARCH_TARGET_MESSAGE = "Please input a valid search target!" +
            "\nExample input: find [SEARCH_TARGET]";

    /**
     * Class constructor.
     */
    public InvalidSearchTargetException() {
        super(INVALID_SEARCH_TARGET_MESSAGE);
    }
}
