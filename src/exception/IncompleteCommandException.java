package exception;

public class IncompleteCommandException extends RuntimeException {
    public static final String message = " cannot be empty! Please enter a valid command!";
    public IncompleteCommandException(String taskName) {
        super(taskName + message);
    }

}
