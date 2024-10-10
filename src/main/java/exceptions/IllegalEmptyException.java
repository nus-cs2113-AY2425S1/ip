package exceptions;

/**
 * Handles errors when user gives an empty description,
 * or when the user forgets to give the description after a keyword.
 */
public class IllegalEmptyException extends Exception {
    public IllegalEmptyException(String message) {
        super(message);
    }
}
