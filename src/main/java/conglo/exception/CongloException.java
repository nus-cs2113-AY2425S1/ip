package conglo.exception;

public class CongloException extends Exception {

    public CongloException(String message) {
        super(message);
    }

    // Custom exception for unknown commands
    public static class UnknownCommand extends CongloException {
        public UnknownCommand() {
            super("Sorry, I'm not smart enough to understand what you mean.");
        }
    }

    // Custom exception for missing description
    public static class MissingDescription extends CongloException {
        public MissingDescription(String type) {
            super("Oopsies, please add a description of " + type + ".");
        }
    }

    // Custom exception for invalid task number
    public static class InvalidTaskNumber extends CongloException {
        public InvalidTaskNumber() {
            super("Invalid task number! Please try again.");
        }
    }

    // Custom exception for missing task number
    public static class MissingTaskNumber extends CongloException {
        public MissingTaskNumber(String action) {
            super("Please provide task number to " + action + ".");
        }
    }

    // Custom exception for invalid input format
    public static class InvalidFormat extends CongloException {
        public InvalidFormat(String type) {
            super(generateMessage(type));
        }

        private static String generateMessage(String type) {
            switch (type) {
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
}
