package jeremy.exception;

/**
 * A base class for all custom exceptions in the application.
 * <p>
 * This class extends {@link Exception} and serves as a base for all specific exceptions
 * related to commands and task management in the application.
 * </p>
 */
public class JeremyException extends Exception {
    /**
     * Constructs a JeremyException with the specified message.
     *
     * @param message The detail message explaining what caused the exception.
     */
    public JeremyException(String message) {
        super(message);
    }
}
