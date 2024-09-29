package conglo.exception;

/**
 * Represents an exception thrown when a task number is missing from the user's input,
 * particularly when attempting to mark, unmark, or delete a task.
 */
public class MissingTaskNumber extends CongloException {

    /**
     * Constructs a new MissingTaskNumber exception with an error message that
     * indicates the action that requires a task number.
     *
     * @param action The action being performed that requires a task number.
     */
    public MissingTaskNumber(String action) {
        super("Please provide task number to " + action + ".");
    }
}
