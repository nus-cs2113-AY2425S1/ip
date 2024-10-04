package exceptions;

/**
 * IllegalIndexException handles error when user give an index
 * that is out of range
 */
public class IllegalIndexException extends Exception {
    public IllegalIndexException(String message) {
        super(message);
    }
}
