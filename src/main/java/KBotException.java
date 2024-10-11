/**
 * Represents exceptions specific to the KBot application.
 * This class extends the Exception class to provide custom error messages.
 */
public class KBotException extends Exception {

    /**
     * Constructs a KBotException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public KBotException(String message) {
        super(message);
    }

    /**
     * Creates a KBotException instance for unknown commands.
     *
     * @return A KBotException indicating that the command is not recognized.
     */
    public static KBotException unknownCommand() {
        return new KBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
