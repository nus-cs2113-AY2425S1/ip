package erika.exception;

public class OutOfBoundsException extends ErikaException{
    public OutOfBoundsException() {
        super("Error: Supplied index is out of bounds");
    }
}
