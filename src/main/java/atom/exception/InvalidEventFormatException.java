package atom.exception;

/**
 * Represents an exception when the <code>event</code> command
 * format is invalid.
 */
public class InvalidEventFormatException extends RuntimeException {

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Incorrect command format!! Try again..\n" +
                "\nMaybe this might be of assistance:" +
                "\n-> \"event <task> /from <date/time> /to <date/time>\"";

        return message;
    }
}
