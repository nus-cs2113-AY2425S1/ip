public class InvalidDeadlineException extends BentoException {
    public static final String INVALID_DEADLINE_MESSAGE = "\tHmm... That doesn't seem quite right. Try \"deadline [DEADLINE_NAME] /by [DEADLINE_BY]\" instead!\n";

    public InvalidDeadlineException() {
        super(INVALID_DEADLINE_MESSAGE);
    }
}
