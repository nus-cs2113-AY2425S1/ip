package erika.exception;
/**
 * Represents the exception thrown when the supplied command is of an unknown type
 */
public class UnknownCommandException extends ErikaException{
    public UnknownCommandException() {
        super("Error: Unknown command");
    }
}
