public class IllegalCommandException extends Exception { // inherit from the Exception
    IllegalCommandException(String message) {
        super(message);
    }
}