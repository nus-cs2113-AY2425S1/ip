public class InvalidCommandException extends AirBorderException {
    public InvalidCommandException() {
        super("Unrecognized Request: Please ensure your input is correct or contact ground staff for assistance.");
    }
}
