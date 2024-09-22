package erika.exception;
/**
 * Represents the exception thrown when the the supplied index of an indexing operation is out of bounds
 */
public class OutOfBoundsException extends ErikaException{
    public OutOfBoundsException() {
        super("Error: Supplied index is out of bounds");
    }
}
