package exception;

/**
 * Signals that task list if full and is unable to accept more tasks.
 */
public class TaskListFullException extends Exception {
    /**
     * Construct TaskListFullException with defined message
     * @param message details of message
     */
    public TaskListFullException(String message) {
        super(message);
    }
}
