package bot;

/**
 * Custom exception for errors specific to TobyBot.
 */
public class TobyBotException extends Exception {

    /**
     * Constructor for TobyBotException.
     *
     * @param message The error message.
     */
    public TobyBotException(String message) {
        super(message);
    }
}
