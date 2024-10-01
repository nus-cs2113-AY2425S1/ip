package jeremy.exception;

/**
 * Represents an exception thrown when an illegal or invalid command is encountered.
 */
public class IllegalCommandException extends JeremyException {
    /**
     * Constructs an IllegalCommandException with the specified message.
     *
     * @param message The detail message explaining what caused the exception.
     */
    public IllegalCommandException(String message) {
        super("Lol, " + message + " is not a valid command.");
    }
}
