package exceptions;

/** Handles error when the user gives an index that is out of range. */
public class IllegalIndexException extends Exception {
    public IllegalIndexException(String message) {
        super(message);
    }
}
