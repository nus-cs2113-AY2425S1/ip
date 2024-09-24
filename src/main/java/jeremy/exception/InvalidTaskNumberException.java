package jeremy.exception;

public class InvalidTaskNumberException extends JeremyException {
    public InvalidTaskNumberException(String message) {
        super("Wow, " + message + " is not even a number.");
    }
}
