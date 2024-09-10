package exception;

public class IllegalCommandException extends Exception { // inherit from the Exception
    public IllegalCommandException(String message) {
        super(message);
    }
}