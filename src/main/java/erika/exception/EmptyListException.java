package erika.exception;

/**
 * Represents the exception thrown when a the <code>TaskList</code> is empty
 */
public class EmptyListException extends ErikaException {
    public EmptyListException() {
        super("Error: List is empty, please consider adding some items!");
    }

}
