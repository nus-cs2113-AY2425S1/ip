public class InvalidToDoException extends BentoException {
    public static final String INVALID_TODO_MESSAGE = "\tHmm... That doesn't seem quite right. Try \"todo [TODO_NAME]\" instead!\n";

    public InvalidToDoException() {
        super(INVALID_TODO_MESSAGE);
    }
}
