public class AirBorderException extends Exception {
    public AirBorderException(String message) {
        super(message);
    }
}

// Specific InvalidCommandException for unrecognized requests
class InvalidCommandException extends AirBorderException {
    public InvalidCommandException() {
        super("Unrecognized Request: Please ensure your input is correct or contact ground staff for assistance.");
    }
}
