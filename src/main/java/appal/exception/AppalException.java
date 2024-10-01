package appal.exception;

/**
 * AppalException handles the default error of invalid input that happen when Appal is running,
 * and shows a message to notify user.
 */
public class AppalException extends Exception {
    public static final String MESSAGE = "Oh no... this is invalid input!";

    /**
     * Class constructor, with default invalid input message.
     */
    public AppalException() {
        super(MESSAGE);
    }

    /**
     * Class constructor specifying the message to show.
     *
     * @param message Specified message to show.
     */
    public AppalException(String message) {
        super(message);
    }
}
