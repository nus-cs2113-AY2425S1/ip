package conglo.exception;

/**
 * Represents an exception thrown when the user provides an invalid task number,
 * such as when the task number is out of range or improperly formatted.
 */
public class InvalidTaskNumber extends CongloException {

    /**
     * Constructs a new InvalidTaskNumber exception with a default error message
     * indicating that the task number is invalid.
     */
    public InvalidTaskNumber() {
        super("Invalid task number! Please try again.");
    }
}
