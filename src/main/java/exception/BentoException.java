package exception;

public class BentoException extends Exception {
    public static final String DEFAULT_MESSAGE = "TASKETE";
    public static final String LINE_MESSAGE = "\t____________________________________________________________\n";

    public BentoException() {
        super(LINE_MESSAGE + DEFAULT_MESSAGE + LINE_MESSAGE);
    }

    public BentoException(String message) {
        super(LINE_MESSAGE + message + LINE_MESSAGE);
    }
}
