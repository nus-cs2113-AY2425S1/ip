package exception;

public class InvalidCommandException extends BentoException {
    public static final String INVALID_COMMAND_MESSAGE = "\tHmm... Something seems wrong with your input. Give it a closer look and try again!\n";

    public InvalidCommandException() {
        super(INVALID_COMMAND_MESSAGE);
    }
}
