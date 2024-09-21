package erika.exception;

public class UnknownCommandException extends ErikaException{
    public UnknownCommandException() {
        super("Error: Unknown command");
    }
}
