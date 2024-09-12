public class InvalidCommandException extends BronException {
    public InvalidCommandException() {
        super("The command entered is not recognized.");
    }
}