package codecatalyst.exception;

public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(String message, String invalidInput) {
        super("Invalid task input \"" + invalidInput + "\". " + message);
    }

    public InvalidTaskNumberException(String message, int taskNumber) {
        super("Invalid task number \"" + taskNumber + "\". " + message);
    }
}
