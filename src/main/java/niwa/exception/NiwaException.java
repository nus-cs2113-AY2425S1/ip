package niwa.exception;

/**
 * The class {@code NiwaException} is a custom exception that represents general exceptions
 * can occur within the {@code Niwa} chatbot.
 */
public class NiwaException extends Exception {
    /**
     * Default constructor for {@code NiwaException}.
     * Calls the superclass constructor.
     */
    public NiwaException() {
        super();
    }

    /**
     * Constructor for {@code NiwaException} that accepts a custom error message.
     *
     * @param message The error message to be associated with this exception.
     */
    public NiwaException(String message) {
        super(message);
    }
}
