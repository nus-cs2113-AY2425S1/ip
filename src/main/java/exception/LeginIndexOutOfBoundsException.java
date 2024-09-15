package exception;

public class LeginIndexOutOfBoundsException extends LeginException{
    private static final String ERROR_MESSAGE = "BROO! No such task index!";
    public LeginIndexOutOfBoundsException() {
        super(ERROR_MESSAGE);
    }
}

