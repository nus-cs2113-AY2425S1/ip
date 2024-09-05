public class InvalidTaskException extends BentoException {
    public static final String INVALID_TASK_MESSAGE = "\tHmm... That doesn't seem quite right. Have you tried providing a task?\n";

    public InvalidTaskException() {
        super(INVALID_TASK_MESSAGE);
    }
}
