package exception;

public class NoTaskFoundException extends BentoException {
    public static final String NO_TASK_FOUND_MESSAGE = "\tHmm... I couldn't find any of the tasks that you " +
            "were searching for, maybe they don't exist?\n";

    public NoTaskFoundException() {
        super(NO_TASK_FOUND_MESSAGE);
    }
}
