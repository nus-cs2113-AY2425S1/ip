public class InvalidIndexException extends BentoException {
    public static final String INVALID_INDEX_MESSAGE = "\tHey! The index provided was not a number!\n";

    public InvalidIndexException() {
        super(INVALID_INDEX_MESSAGE);
    }
}
