public class IncompleteCommandException extends RuntimeException {
    public IncompleteCommandException(String message) {
        super(message);
    }
    public IncompleteCommandException(String taskName, String message) {
        super(taskName + message);
    }

}
