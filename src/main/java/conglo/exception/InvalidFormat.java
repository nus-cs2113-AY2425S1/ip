package conglo.exception;

/**
 * Represents an exception thrown when a user inputs a command in an incorrect format.
 */
public class InvalidFormat extends CongloException {

    /**
     * Constructs a new InvalidFormat exception with a specific error message
     * based on the type of command that was formatted incorrectly.
     *
     * @param type the type of command that was incorrectly formatted
     */
    public InvalidFormat(String type) {
        super(generateMessage(type));
    }

    /**
     * Generates a error message based on the type of the incorrectly
     * formatted command.
     *
     * @param type The type of command.
     * @return a string containing the error message suggesting the correct format.
     */
    private static String generateMessage(String type) {
        switch (type) {
        case "list":
            return "Do you mean 'list'?";
        case "deadline":
            return "Oh dear, your deadline command is a bit off."
                    + System.lineSeparator() + "Please use: deadline [description] /by [deadline].";
        case "event":
            return "Oh no! Your event command needs a bit of tweaking."
                    + System.lineSeparator() + "Try: event [description] /from [start time] /to [end time].";
        default:
            return "Oops! Looks like there's a hiccup in your command."
                    + System.lineSeparator() + "Please follow the correct format.";
        }
    }
}
