package conglo.exception;

public class InvalidFormat extends CongloException {
    public InvalidFormat(String type) {
        super(generateMessage(type));
    }

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
