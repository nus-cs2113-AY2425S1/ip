public class InvalidEventException extends  BentoException {
    public static final String INVALID_EVENT_MESSAGE = "\tHmm... That doesn't seem quite right. Try \"event [EVENT_NAME] /from [FROM] /to [TO]\" instead!\n";

    public InvalidEventException() {
        super(INVALID_EVENT_MESSAGE);
    }
}
