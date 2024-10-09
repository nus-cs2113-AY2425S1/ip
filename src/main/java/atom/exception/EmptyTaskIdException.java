package atom.exception;

/**
 * Represents an exception when the task id in <code>mark</code>, <code>unmark</code>
 * and <code>delete</code> commands is missing.
 */
public class EmptyTaskIdException extends RuntimeException {

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Please specify the task id!!";
        return message;
    }
}
