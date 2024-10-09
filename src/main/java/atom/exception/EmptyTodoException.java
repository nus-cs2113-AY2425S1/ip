package atom.exception;

/**
 * Represents an exception when the task name in <code>todo</code>
 * command is empty.
 */
public class EmptyTodoException extends RuntimeException {

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Erm... what's the name of the task again??";
        return message;
    }
}
