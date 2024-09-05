public class InvalidIndexException extends BentoException {
    public static final String INVALID_INDEX_MESSAGE = "\tHey! Provide a valid index please!\n";

    public InvalidIndexException() {
        super(INVALID_INDEX_MESSAGE);
    }
}
