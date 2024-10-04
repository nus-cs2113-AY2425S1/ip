package exceptions;

/**
 * IllegalCommandException handles errors when user gives an undefined command
 */
public class IllegalCommandException extends Exception {
    public IllegalCommandException(String message) {
        super(message);
    }
}
