package exception;

/**
 * Represents an exception thrown when the user provides an invalid index to Bento.
 * This class extends {@link BentoException} and is used to notify the user when the provided
 * index is out of bounds or invalid.
 */
public class InvalidIndexException extends BentoException {
    /** The default message displayed when the user provides an invalid index. */
    public static final String INVALID_INDEX_MESSAGE = "\tHey! Provide a valid index please!\n";

    /**
     * Constructs a new InvalidIndexException with the default error message.
     */
    public InvalidIndexException() {
        super(INVALID_INDEX_MESSAGE);
    }
}
