package conglo.exception;

/**
 * Represents an exception thrown when a user inputs an unrecognized command.
 */
public class UnknownCommand extends CongloException {

    /**
     * Constructs an UnknownCommand exception with a message.
     */
    public UnknownCommand() {
        super("Sorry, I'm not smart enough to understand what you mean.");
    }
}
