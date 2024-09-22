package erika.exception;
/**
 * Represents the generic exception unique to the Erika chatbot
 */
public class ErikaException extends RuntimeException {
    public ErikaException(String message) {
        super(message);
    }
    public String getMessage() {
        return super.getMessage();
    }
}
