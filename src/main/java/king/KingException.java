package king;

/**
 * Custom exception class for King.
 * Represents exceptions/errors specific to the chatbot.
 */
public class KingException extends Exception {

    /**
     * Constructs a KingException with the specified detail message.
     *
     * @param message The detail message that describes the cause of the exception.
     */
    protected KingException(String message) {
        super(message);
    }
}
