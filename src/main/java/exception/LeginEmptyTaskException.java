package exception;

public class LeginEmptyTaskException extends LeginException{
    private static final String ERROR_MESSAGE = "BROO! No task description is given!";
    public LeginEmptyTaskException() {
        super(ERROR_MESSAGE);
    }
}
