package jeremy.exception;

public class IllegalCommandException extends JeremyException {
    public IllegalCommandException(String message) {
        super("Lol, " + message + " is not a valid command.");
    }
}
