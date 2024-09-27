package appal.exception;

public class UnspecifiedDeadlineException extends AppalException {
    public static final String UNSPECIFIED_DEADLINE_MESSAGE = "Remember to specify a deadline, pal!" +
            "\nExample input: deadline [TASK] /by [DEADLINE FORMAT yyyy-mm-dd]";

    public UnspecifiedDeadlineException() {
        super(UNSPECIFIED_DEADLINE_MESSAGE);
    }
}
