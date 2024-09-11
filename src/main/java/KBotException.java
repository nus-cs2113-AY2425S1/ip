public class KBotException extends Exception {
    public KBotException(String message) {
        super(message);
    }

    // New method for handling unknown commands
    public static KBotException unknownCommand() {
        return new KBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
