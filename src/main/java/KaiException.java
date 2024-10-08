/**
 * Custom exception class for handling errors specific to the KaiWen chatbot.
 * Inherits from the Exception class and provides an error message.
 */
public class KaiException extends Exception {

    // Constructs a new KaiException with the specified detail message.
    public KaiException(String message) {
        super(message);
    }
}
