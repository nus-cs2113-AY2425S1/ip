package UranusExceptions;

/**
 * Represents an exception that is thrown when the user
 * enters an unrecognized or illegal command.
 * It extends the UranusExceptions class and provides a specific error message.
 */
public class IllegalCommandException extends UranusExceptions {
    /**
     * Constructs a new IllegalCommandException.
     * This exception is triggered when the user enters a command
     * that does not exist or is not recognized.
     */
    public IllegalCommandException() {
        super();
    }

    /**
     * Returns a specific error message when the user enters an illegal command.
     *
     * @return A string message indicating that the command does not exist.
     */
    @Override
    public String getMessage() {
      return "No such commands exist. Do check what I can do again";
    }
}
