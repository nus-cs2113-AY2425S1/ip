package exception;

/**
 * Represents exceptions specific to the Lia chatbot.
 */
public class LiaException extends Exception {

    /**
     * Creates a new LiaException with a specified error message.
     *
     * @param message The error message to be displayed.
     */
    public LiaException(String message) {
        super(message);
    }
}
