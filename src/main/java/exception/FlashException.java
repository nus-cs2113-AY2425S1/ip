package exception;

/**
 * Signals an error specific to the Flash application.
 *
 * @param message should provide details about the error.
 */
public class FlashException extends Exception {
    public FlashException(String message) {
        super(message);
    }
}
