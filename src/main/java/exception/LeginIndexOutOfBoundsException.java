package exception;

/**
 * Signals that the index the user input is out of the range of the task list array
 */
public class LeginIndexOutOfBoundsException extends LeginException{
    private static final String ERROR_MESSAGE = "BROO! No such task index!";
    public LeginIndexOutOfBoundsException() {
        super(ERROR_MESSAGE);
    }
}

