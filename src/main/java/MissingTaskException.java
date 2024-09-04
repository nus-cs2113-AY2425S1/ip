public class MissingTaskException extends BentoException {
    public static final String NO_TASK_FOUND_MESSAGE = "\tHmm... I don't think that task exists... Check again with list!\n";

    public MissingTaskException() {
        super(NO_TASK_FOUND_MESSAGE);
    }
}
